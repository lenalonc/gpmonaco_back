package com.gpmonaco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "company")
    private String company;

    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "postal_code", nullable = false)
    private int postalCode;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;

}
