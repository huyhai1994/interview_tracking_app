package com.homelab.interview_tracking_app.job_post.create_jobposts.controller;


import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.company.share.repositories.CompanyRepository;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.job_platform.shared.repositories.JobPlatformRepository;
import com.homelab.interview_tracking_app.job_post.shared.entities.JobPost;
import com.homelab.interview_tracking_app.job_post.shared.repositories.JobPostRepository;
import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import com.homelab.interview_tracking_app.support.MockCompany;
import com.homelab.interview_tracking_app.support.MockJobPlatform;
import com.jayway.jsonpath.JsonPath;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class JobPostCreationControllerIntegrationTest
        extends AbstractIntegrationTest {

    private static final String JOB_POST_URI = "/api/v1/jobposts";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPlatformRepository jobPlatformRepository;

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void create_whenValidRequest_thenReturnCreated() throws Exception {

        // Given
        Company company = companyRepository.saveAndFlush(
                MockCompany.samsung()
        );

        JobPlatform platform = jobPlatformRepository.saveAndFlush(
                MockJobPlatform.topCV()
        );

        String requestBody = """
                {
                    "title": "Java Backend Developer",
                    "apply_end_at": "2027-12-31T23:59:59Z",
                    "experience_min": 2,
                    "experience_max": 5,
                    "url": "https://example.com/jobs/java",
                    "company_id": %d,
                    "job_platform_id": %d
                }
                """.formatted(company.getId(), platform.getId());

        // When
        MvcResult result = mockMvc.perform(
                        post(JOB_POST_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.title")
                        .value("Java Backend Developer"))
                .andExpect(jsonPath("$.data.company_name")
                        .value(company.getName()))
                .andReturn();

        Integer id = JsonPath.read(
                result.getResponse().getContentAsString(),
                "$.data.id"
        );

        // Flush and clear Persistence Context
        entityManager.flush();
        entityManager.clear();

        // Verify database
        JobPost saved = jobPostRepository
                .findJobPostById(id)
                .orElseThrow();

        assertThat(saved.getTitle())
                .isEqualTo("Java Backend Developer");

        assertThat(saved.getExperienceMin()).isEqualTo(2);
        assertThat(saved.getExperienceMax()).isEqualTo(5);

        assertThat(saved.getApplyEndAt())
                .isEqualTo(LocalDateTime.of(
                        2027, 12, 31, 23, 59, 59
                ));

        assertThat(saved.getCompany().getId())
                .isEqualTo(company.getId());

        assertThat(saved.getJobPlatform().getId())
                .isEqualTo(platform.getId());
    }

    @Test
    void create_whenExperienceRangeInvalid_thenReturnBadRequest()
            throws Exception {

        String requestBody = """
                {
                    "title": "Java Developer",
                    "apply_end_at": "2027-12-31T23:59:59Z",
                    "experience_min": 5,
                    "experience_max": 2,
                    "company_id": 1,
                    "job_platform_id": 1
                }
                """;

        mockMvc.perform(
                        post(JOB_POST_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isBadRequest());
    }
}