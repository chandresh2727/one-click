package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.Admin;
import com.ecommerce.E_commerce.entity.Client;
import com.ecommerce.E_commerce.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginClient {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    com.ecommerce.E_commerce.repository.AdminRepo adminRepo;

    public Object saveClient(Client client){

        List<Admin> admins =  adminRepo.findAll();

//        System.out.println("All the admin: "+adminRepo.findAll().toString());

        if(admins == null){
            throw new RuntimeException("Not Found");
        }

        clientRepo.save(client);

        List<Client> savedClients = new ArrayList<>();

        for(Admin admin: admins){
            client.setAdmin(admin);
            Client savedClient = clientRepo.save(client);
            savedClients.add(savedClient);
        }

        return savedClients;
    }

}
