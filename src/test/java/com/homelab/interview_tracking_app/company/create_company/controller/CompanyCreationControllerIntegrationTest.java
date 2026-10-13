package com.homelab.interview_tracking_app.company.create_company.controller;

import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.company.share.repositories.CompanyRepository;
import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import com.homelab.interview_tracking_app.support.MockCompany;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class CompanyCreationControllerIntegrationTest
        extends AbstractIntegrationTest {

    private static final String COMPANY_URI = "/api/v1/companies";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void create_whenCreateNewCompanyThenReturnResponse() throws Exception {

        // Given
        Company company = MockCompany.samsung();

        String requestBody = """
                {
                    "name": "%s",
                    "taxCode": "%s",
                    "address": "%s",
                    "website": "%s"
                }
                """.formatted(
                company.getName(),
                company.getTaxCode(),
                company.getAddress(),
                company.getWebsite()
        );

        // When & Then
        mockMvc.perform(post(COMPANY_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.name").value(company.getName()))
                .andExpect(jsonPath("$.data.taxCode").value(company.getTaxCode()))
                .andExpect(jsonPath("$.data.website").value(company.getWebsite()));

        // Verify database
        Company savedCompany = companyRepository.findAll()
                .stream()
                .filter(c -> c.getTaxCode().equals(company.getTaxCode()))
                .findFirst()
                .orElseThrow();

        assertThat(savedCompany.getId()).isNotNull();
        assertThat(savedCompany.getName()).isEqualTo(company.getName());
        assertThat(savedCompany.getTaxCode()).isEqualTo(company.getTaxCode());
        assertThat(savedCompany.getAddress()).isEqualTo(company.getAddress());
    }
}