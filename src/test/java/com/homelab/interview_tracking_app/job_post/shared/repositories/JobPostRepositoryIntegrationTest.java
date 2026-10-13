package com.homelab.interview_tracking_app.job_post.shared.repositories;

import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.company.share.repositories.CompanyRepository;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.job_platform.shared.repositories.JobPlatformRepository;
import com.homelab.interview_tracking_app.job_post.shared.entities.JobPost;
import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import com.homelab.interview_tracking_app.support.MockCompany;
import com.homelab.interview_tracking_app.support.MockJobPlatform;
import com.homelab.interview_tracking_app.support.MockJobPost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class JobPostRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    JobPlatformRepository jobPlatformRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void save_whenSaveAnPostSuccess_thenCouldQueryIt() {
        Company company = companyRepository.save(MockCompany.samsung());
        JobPlatform jobPlatform = jobPlatformRepository.save(MockJobPlatform.topCV());
        Integer jobPostId = jobPostRepository.save(MockJobPost.backendIntern(company, jobPlatform)).getId();
        JobPost jobPost = jobPostRepository.findJobPostById(jobPostId).orElseThrow();
        assertThat(jobPost.getCompany().getTaxCode()).isEqualTo(company.getTaxCode());
        assertThat(jobPost.getJobPlatform().getName()).isEqualTo(jobPlatform.getName());
    }


}