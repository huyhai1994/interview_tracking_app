package com.homelab.interview_tracking_app.job_platform.create_jobplatform.service;

import com.homelab.interview_tracking_app.job_platform.create_jobplatform.dto.JobPlatformCreationRequest;
import com.homelab.interview_tracking_app.job_platform.create_jobplatform.dto.JobPlatformCreationResponse;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import com.homelab.interview_tracking_app.job_platform.shared.repositories.JobPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobPlatformCreationService {

    private final JobPlatformRepository jobPlatformRepository;

    @Transactional
    public JobPlatformCreationResponse create(
            JobPlatformCreationRequest request
    ) {

        JobPlatform jobPlatform = new JobPlatform();
        jobPlatform.setName(request.name());

        JobPlatform saved = jobPlatformRepository.save(jobPlatform);

        return new JobPlatformCreationResponse(
                saved.getId(),
                saved.getName()
        );
    }
}