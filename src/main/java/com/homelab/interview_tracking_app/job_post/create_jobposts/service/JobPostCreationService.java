package com.homelab.interview_tracking_app.job_post.create_jobposts.service;

import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.company.share.repositories.CompanyRepository;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.job_platform.shared.repositories.JobPlatformRepository;
import com.homelab.interview_tracking_app.job_post.create_jobposts.dto.JobPostCreationRequest;
import com.homelab.interview_tracking_app.job_post.create_jobposts.dto.JobPostCreationResponse;
import com.homelab.interview_tracking_app.job_post.shared.entities.JobPost;
import com.homelab.interview_tracking_app.job_post.shared.repositories.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class JobPostCreationService {

    private final JobPostRepository jobPostRepository;
    private final CompanyRepository companyRepository;
    private final JobPlatformRepository jobPlatformRepository;

    @Transactional
    public JobPostCreationResponse save(JobPostCreationRequest request) {

        if (request.experienceMin() != null
                && request.experienceMax() != null
                && request.experienceMin() > request.experienceMax()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "experience_min must not exceed experience_max"
            );
        }

        Company company = companyRepository
                .findById(request.companyId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company not found: " + request.companyId()
                ));

        JobPlatform jobPlatform = jobPlatformRepository
                .findById(request.jobPlatformId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job platform not found: " + request.jobPlatformId()
                ));

        // Build entity
        JobPost jobPost = buildEntity(request, company, jobPlatform);

        // Persist
        JobPost saved = jobPostRepository.save(jobPost);

        // Response
        return new JobPostCreationResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getUrl(),
                company.getName()
        );
    }

    private static @NonNull JobPost buildEntity(JobPostCreationRequest request, Company company, JobPlatform jobPlatform) {
        JobPost jobPost = new JobPost();

        jobPost.setTitle(request.title());

        jobPost.setApplyEndAt(
                LocalDateTime.ofInstant(
                        request.applyEndAt(),
                        ZoneOffset.UTC
                )
        );

        jobPost.setExperienceMin(request.experienceMin());
        jobPost.setExperienceMax(request.experienceMax());
        jobPost.setUrl(request.url());

        jobPost.setCompany(company);
        jobPost.setJobPlatform(jobPlatform);
        return jobPost;
    }
}