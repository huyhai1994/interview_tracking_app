package com.homelab.interview_tracking_app.company.share.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "taxcode", unique = true)
    private String taxCode;

    @Column(name = "address")
    private String address;

    @Column(name = "website", length = 2048)
    private String website;
}
