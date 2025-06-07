package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.Client;
import com.ecommerce.E_commerce.entity.Selection;
import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.repository.CartRepository;
import com.ecommerce.E_commerce.repository.ClientRepo;
import com.ecommerce.E_commerce.repository.ProductRepository;
import com.ecommerce.E_commerce.repository.SelectionRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import com.ecommerce.E_commerce.entity.Cart;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SelectionService {

    private final SelectionRepository selectionRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ClientRepo clientRepo;
    private final EmailService emailService;

    @Value("${admin.email}")
    private String adminEmail;

    public SelectionService(SelectionRepository selectionRepository, CartRepository cartRepository,
                            ProductRepository productRepository, ClientRepo clientRepo,
                            EmailService emailService) {
        this.selectionRepository = selectionRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.clientRepo = clientRepo;
        this.emailService = emailService;
    }

    public List<Selection> getSelectionByClient(String clientName) {
        return selectionRepository.findByClient_ClientName(clientName);
    }

    // Confirm the selection and send email to client and admin
    public void finalizeSelection(String clientName, String clientEmail, String clientPhone) throws MessagingException {
        Client client = clientRepo.findByClientName(clientName)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Fetch all carts for client
        List<Cart> carts = cartRepository.findByClient_ClientName(clientName);
        if (carts.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        StringBuilder clientMailContent = new StringBuilder("<h3>Thank you for your order!</h3><ul>");
        StringBuilder adminMailContent = new StringBuilder("<h3>New Order Placed by " + clientName + "</h3><ul>");

        for (com.ecommerce.E_commerce.entity.Cart cart : carts) {
            Product product = cart.getProduct();
            int qty = cart.getQuantity();

            // Reduce product stock
            int newStock = product.getStock() - qty;
            if (newStock < 0) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
            product.setStock(newStock);
            productRepository.save(product);

            // Create Selection entry
            Selection selection = new Selection();
            selection.setClient(client);
            selection.setProduct(product);
            selection.setQuantity(qty);
            selection.setClientEmail(clientEmail);
            selection.setClientPhone(clientPhone);
            selection.setConfirmed(true);
            selection.setConfirmedDate(LocalDate.now());
            selectionRepository.save(selection);

            // Append email content
            clientMailContent.append("<li>Product: ").append(product.getName())
                    .append(", Category: ").append(product.getCategory().getName())
                    .append(", Quantity: ").append(qty)
                    .append(", Price: $").append(product.getPrice() * qty)
                    .append("</li>");

            adminMailContent.append("<li>Client: ").append(clientName)
                    .append(", Product: ").append(product.getName())
                    .append(", Category: ").append(product.getCategory().getName())
                    .append(", Quantity: ").append(qty)
                    .append(", Price: $").append(product.getPrice() * qty)
                    .append("</li>");
        }

        clientMailContent.append("</ul><p>We appreciate your business!</p>");
        adminMailContent.append("</ul>");

        // Clear client's cart after confirmation
        cartRepository.deleteAll(carts);

        // Send emails
        emailService.sendEmail(clientEmail, "Order Confirmation", clientMailContent.toString());

        // If admin email is set, send admin mail
        if (adminEmail != null && !adminEmail.isEmpty()) {
            emailService.sendEmail(adminEmail, "New Client Order: " + clientName, adminMailContent.toString());
        }
    }

    // Admin daily summary email
    public List<Selection> getTodaysConfirmedSelections() {
        LocalDate today = LocalDate.now();
        return selectionRepository.findByConfirmedDate(today);
    }

    public void sendDailySummaryToAdmin() throws MessagingException {
        List<Selection> todaysSelections = getTodaysConfirmedSelections();
        if (todaysSelections.isEmpty()) return;

        StringBuilder sb = new StringBuilder("<h3>Daily Order Summary</h3><ul>");
        for (Selection sel : todaysSelections) {
            Product p = sel.getProduct();
            sb.append("<li>")
                    .append("Client Email: ").append(sel.getClientEmail()).append(", ")
                    .append("Product: ").append(p.getName()).append(", ")
                    .append("Category: ").append(p.getCategory().getName()).append(", ")
                    .append("Quantity: ").append(sel.getQuantity()).append(", ")
                    .append("Total: $").append(p.getPrice() * sel.getQuantity())
                    .append("</li>");
        }
        sb.append("</ul>");

        if (adminEmail != null && !adminEmail.isEmpty()) {
            emailService.sendEmail(adminEmail, "Daily Order Summary", sb.toString());
        }
    }

}
