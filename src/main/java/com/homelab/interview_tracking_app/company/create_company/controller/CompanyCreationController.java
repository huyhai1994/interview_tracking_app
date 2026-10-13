package com.homelab.interview_tracking_app.company.create_company.controller;

import com.homelab.interview_tracking_app.company.create_company.dto.CompanyCreationRequest;
import com.homelab.interview_tracking_app.company.create_company.dto.CompanyCreationResponse;
import com.homelab.interview_tracking_app.company.create_company.service.CompanyCreationService;
import com.homelab.interview_tracking_app.shared.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/companies")
public class CompanyCreationController {
    private final CompanyCreationService companyCreationService;

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyCreationResponse>> createCompany(@RequestBody CompanyCreationRequest request) {
        CompanyCreationResponse response = companyCreationService.create(request);
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(ApiResponse.success(response));
    }
}
