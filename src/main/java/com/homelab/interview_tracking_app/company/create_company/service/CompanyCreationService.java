package com.homelab.interview_tracking_app.company.create_company.service;

import com.homelab.interview_tracking_app.company.create_company.dto.CompanyCreationRequest;
import com.homelab.interview_tracking_app.company.create_company.dto.CompanyCreationResponse;
import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.company.share.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyCreationService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyCreationResponse create(CompanyCreationRequest request) {

        Company company = new Company();
        company.setName(request.name());
        company.setTaxCode(request.taxCode());
        company.setAddress(request.address());
        company.setWebsite(request.website());

        Company savedCompany = companyRepository.save(company);

        return new CompanyCreationResponse(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getTaxCode(),
                savedCompany.getWebsite()
        );
    }
}