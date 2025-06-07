package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.repository.ClientRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.E_commerce.entity.Admin;

import java.util.Collections;
import java.util.List;

@Service
public class loginAdmin {

    @Autowired
    private com.ecommerce.E_commerce.repository.AdminRepo adminRepo;

    @Autowired
    private ClientRepo clientRepo;

    public Object saveAdmin(Admin admin){
            adminRepo.save(admin);
            return admin;
        }

    public List<Admin> signupAdmin(String name, String password){
        if(adminRepo.existsByAdminName(name) && adminRepo.existsByAdminPassword(password)){
           return (List<Admin>) adminRepo.findByAdminName(name);
        }
        Admin admin = new Admin();
        admin.setAdminName("DUMMY-TEST");

        return Collections.singletonList(admin);//Collections.singletonList("please enter valid user detais");
    }

    @Transactional
    public void deleteAdmin(String admin_name) {
        adminRepo.deleteByAdminName(admin_name);
    }

    public String listProduct(){
        return null;
    }

//    public List<Client> clientDetails(String name, String emailId, Long phoneNumber){
//
//        List<Client> clients = new ArrayList<>();
//
//        if(clientRepo.existsByClientName(name)){
//            clients.add(clientRepo.findByNameEmailAndPhoneNumber(name,emailId,phoneNumber));
//        }
//        else{
//            throw new EntityNotFoundException("Client name: "+name+"Does not exist");
//        }
//        return clients;
//    }
}
