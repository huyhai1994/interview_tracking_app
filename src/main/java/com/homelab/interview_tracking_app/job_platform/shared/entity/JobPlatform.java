package com.homelab.interview_tracking_app.job_platform.shared.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_platform")
@Getter
@Setter
public class JobPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;
}
