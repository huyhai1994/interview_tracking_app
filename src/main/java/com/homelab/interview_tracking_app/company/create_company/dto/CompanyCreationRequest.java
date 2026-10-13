package com.homelab.interview_tracking_app.company.create_company.dto;

import lombok.NonNull;

public record CompanyCreationRequest(
        @NonNull String name,
        @NonNull String taxCode,
        String address,
        String website
) {
}
