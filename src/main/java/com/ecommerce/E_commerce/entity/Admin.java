package com.ecommerce.E_commerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adminName",length = 25)
    @NonNull
    private String adminName;

    @Column(name = "adminEmailId",length = 25)
    @NonNull
    private String adminEmailId;

    @Column(name = "adminPhoneNumber",length = 10)
    @NonNull
    private Long adminPhoneNumber;

    @Column(name = "adminPassword",length = 20)
    @NonNull
    private String adminPassword;

    @OneToMany(mappedBy = "admin")
    @JsonManagedReference
    private List<Client> clients = new ArrayList<>();

//    @OneToMany(mappedBy = "clientAdmin")
//    private List<client> clients;

}
