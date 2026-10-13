package com.homelab.interview_tracking_app.job_platform.shared.repositories;

import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import com.homelab.interview_tracking_app.support.MockJobPlatform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class JobPlatformRepositoryIntegrationTest
        extends AbstractIntegrationTest {

    @Autowired
    private JobPlatformRepository jobPlatformRepository;

    @Test
    void save_whenCreateNewPlatformThenPersistSuccessfully() {

        // Given
        JobPlatform platform = MockJobPlatform.topCV();

        // When
        JobPlatform saved = jobPlatformRepository.saveAndFlush(platform);

        // Then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("TopCV");

        JobPlatform found = jobPlatformRepository
                .findById(saved.getId())
                .orElseThrow();

        assertThat(found.getName()).isEqualTo(platform.getName());
    }

    @Test
    void saveAll_whenCreateMultiplePlatformsThenPersistSuccessfully() {

        // Given
        List<JobPlatform> platforms = MockJobPlatform.all();

        // When
        List<JobPlatform> saved =
                jobPlatformRepository.saveAllAndFlush(platforms);

        // Then
        assertThat(saved).hasSize(5);

        assertThat(saved)
                .extracting(JobPlatform::getId)
                .doesNotContainNull();

        assertThat(saved)
                .extracting(JobPlatform::getName)
                .containsExactlyInAnyOrder(
                        "TopCV",
                        "VietnamWorks",
                        "LinkedIn",
                        "ITviec",
                        "CareerViet"
                );
    }
}