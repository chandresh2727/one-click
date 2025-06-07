package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.service.SelectionService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/selection")
public class SelectionController {

    private final SelectionService selectionService;

    public SelectionController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @PostMapping("/finalize")
    public ResponseEntity<String> finalizeSelection(@RequestParam String clientName,
                                                    @RequestParam String email,
                                                    @RequestParam String phone) throws MessagingException {
        try {
            selectionService.finalizeSelection(clientName, email, phone);
            return ResponseEntity.ok("Order confirmed and email sent.");
        }  catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
