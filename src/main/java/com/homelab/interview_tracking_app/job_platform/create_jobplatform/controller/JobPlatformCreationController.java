package com.homelab.interview_tracking_app.job_platform.create_jobplatform.controller;

import com.homelab.interview_tracking_app.job_platform.create_jobplatform.dto.JobPlatformCreationRequest;
import com.homelab.interview_tracking_app.job_platform.create_jobplatform.dto.JobPlatformCreationResponse;
import com.homelab.interview_tracking_app.job_platform.create_jobplatform.service.JobPlatformCreationService;
import com.homelab.interview_tracking_app.shared.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobPlatforms")
@RequiredArgsConstructor
public class JobPlatformCreationController {

    private final JobPlatformCreationService jobPlatformCreationService;

    @PostMapping
    public ResponseEntity<ApiResponse<JobPlatformCreationResponse>> createJobPlatform(
            @RequestBody JobPlatformCreationRequest request
    ) {

        JobPlatformCreationResponse response =
                jobPlatformCreationService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }
}