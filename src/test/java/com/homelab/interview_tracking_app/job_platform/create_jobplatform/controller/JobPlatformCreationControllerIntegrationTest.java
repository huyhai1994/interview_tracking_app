package com.homelab.interview_tracking_app.job_platform.create_jobplatform.controller;

import com.homelab.interview_tracking_app.job_platform.create_jobplatform.dto.JobPlatformCreationRequest;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.job_platform.shared.repositories.JobPlatformRepository;
import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import com.homelab.interview_tracking_app.support.configuration.ObjectMapperTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Import(ObjectMapperTest.class)
class JobPlatformCreationControllerIntegrationTest
        extends AbstractIntegrationTest {

    private static final String JOB_PLATFORM_URI =
            "/api/v1/jobPlatforms";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JobPlatformRepository jobPlatformRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void create_whenCreateNewPlatform_thenReturnResponse()
            throws Exception {

        // Given
        JobPlatformCreationRequest request =
                new JobPlatformCreationRequest("TopCV");

        String requestBody =
                objectMapper.writeValueAsString(request);

        // When
        String responseBody = mockMvc.perform(
                        post(JOB_PLATFORM_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.name").value("TopCV"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then - verify database
        JsonNode response = objectMapper.readTree(responseBody);

        Integer id = response
                .path("data")
                .path("id")
                .asInt();

        JobPlatform saved = jobPlatformRepository
                .findById(id)
                .orElseThrow();

        assertThat(saved.getId()).isEqualTo(id);
        assertThat(saved.getName()).isEqualTo("TopCV");

        log.info("Created JobPlatform: id={}, name={}",
                saved.getId(),
                saved.getName());
    }
}