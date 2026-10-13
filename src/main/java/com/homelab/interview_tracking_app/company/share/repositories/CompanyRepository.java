package com.homelab.interview_tracking_app.company.share.repositories;

import com.homelab.interview_tracking_app.company.share.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
