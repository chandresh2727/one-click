package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.entity.Admin;
import com.ecommerce.E_commerce.entity.Client;
import com.ecommerce.E_commerce.repository.ClientRepo;
import com.ecommerce.E_commerce.service.LoginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    LoginClient loginClient;

    @Autowired
    com.ecommerce.E_commerce.repository.AdminRepo adminRepo;

    @PostMapping("/login")
    public Object loginAdmin(@RequestBody Client client){
        if(clientRepo.existsByClientName(client.getClientName())){
            return "User all ready Exist";
        }
        loginClient.saveClient(client);
        return client;
    }

    // Some of the client can see admins details
    @GetMapping("/client/admins")
    public List<Admin> getAllAdmin(@RequestParam String name){

        boolean nameExsits = clientRepo.existsByClientName(name);
        List<Admin> allAdmin = null;
        if(nameExsits){
            allAdmin = adminRepo.findAll();
        }

        return allAdmin;

    }

}
