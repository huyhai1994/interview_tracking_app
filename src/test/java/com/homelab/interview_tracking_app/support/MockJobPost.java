package com.homelab.interview_tracking_app.support;

import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.job_post.shared.entities.JobPost;

import java.time.LocalDateTime;
import java.util.List;

public final class MockJobPost {

    private MockJobPost() {
    }

    public static JobPost create(
            Company company,
            JobPlatform jobPlatform,
            String title,
            LocalDateTime applyEndAt,
            Integer experienceMin,
            Integer experienceMax,
            String url
    ) {
        JobPost post = new JobPost();
        post.setCompany(company);
        post.setJobPlatform(jobPlatform);
        post.setTitle(title);
        post.setApplyEndAt(applyEndAt);
        post.setExperienceMin(experienceMin);
        post.setExperienceMax(experienceMax);
        post.setUrl(url);
        return post;
    }

    public static JobPost javaBackend(
            Company company,
            JobPlatform jobPlatform
    ) {
        return create(
                company,
                jobPlatform,
                "Java Backend Developer",
                LocalDateTime.of(2027, 12, 31, 23, 59, 59),
                2,
                5,
                "https://example.com/jobs/java-backend"
        );
    }

    public static JobPost springBootBackend(
            Company company,
            JobPlatform jobPlatform
    ) {
        return create(
                company,
                jobPlatform,
                "Spring Boot Backend Developer",
                LocalDateTime.of(2027, 11, 30, 23, 59, 59),
                1,
                3,
                "https://example.com/jobs/spring-boot"
        );
    }

    public static JobPost backendIntern(
            Company company,
            JobPlatform jobPlatform
    ) {
        return create(
                company,
                jobPlatform,
                "Backend Developer Intern",
                LocalDateTime.of(2027, 10, 31, 23, 59, 59),
                0,
                1,
                "https://example.com/jobs/backend-intern"
        );
    }

    public static List<JobPost> all(
            Company company,
            JobPlatform jobPlatform
    ) {
        return List.of(
                javaBackend(company, jobPlatform),
                springBootBackend(company, jobPlatform),
                backendIntern(company, jobPlatform)
        );
    }
}