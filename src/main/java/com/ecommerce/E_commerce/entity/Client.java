package com.ecommerce.E_commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "clientName" , length = 25)
    @NonNull
    private String clientName;

    @Column(name = "clientEmailId" , length = 25)
    @NonNull
    private String clientEmailId;

    @Column(name = "clientPhoneNumber" , length = 10)
    @NonNull
    private Long clientPhoneNumber;

    @Column(name = "description" , length = 250)
    @NonNull
    private String description;

    @Column(name = "dob")
//    @DateTimeFormat(iso = DateFormat.ISO.DATE)
    private LocalDate dob;

    @Column(name = "clientPassword", length = 20)
    private String clientPassword;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    @JsonBackReference
    private Admin admin;

//    @ManyToOne()
//    private List<admin> clientAdmin;

}
