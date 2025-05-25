package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.Entity.Admin;
import com.ecommerce.E_commerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private com.ecommerce.E_commerce.repository.AdminRepo adminRepo;

    @Autowired
    private com.ecommerce.E_commerce.service.loginAdmin  loginAdmin;

    @Autowired
    private ClientRepo clientRepo;

    @PostMapping("/login")
    public Object loginAdmin(@RequestBody Admin admin){
//        System.out.println(adminRepo.existsByAdminName(admin.getAdminName()));
        if(adminRepo.existsByAdminName(admin.getAdminName())){
            return "User all ready Exist";
        }
        loginAdmin.saveAdmin(admin);
        return admin;
    }

    @PostMapping("/sign-up")
    public List<Admin> signUpAdmin(@RequestBody Admin admin){

        return loginAdmin.signupAdmin(admin.getAdminName(),admin.getAdminPassword());

    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(required = false) String adminName){
        if(adminName == null || adminName.isEmpty()) return "Admin not exists!!!!";
        loginAdmin.deleteAdmin(adminName);
        return "This admin is deleted: "+adminName;
    }

    @PostMapping("/pType")
    public String productType(@RequestParam(required = false) String pType){
         return null;
    }



//    @GetMapping("/all/clients")
//    public List<Client> allClient(@RequestParam String name,@RequestParam(required = false) String emailId, @RequestParam(required = false) Long phoneNumber){
//         return loginAdmin.clientDetails(name,emailId,phoneNumber);
//    }

}
