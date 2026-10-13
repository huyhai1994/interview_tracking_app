package com.homelab.interview_tracking_app.company.create_company.dto;

public record CompanyCreationResponse(
        Integer id,
        String name,
        String taxCode,
        String website
) {
}
