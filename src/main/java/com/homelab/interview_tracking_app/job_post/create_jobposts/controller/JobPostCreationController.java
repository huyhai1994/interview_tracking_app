package com.homelab.interview_tracking_app.job_post.create_jobposts.controller;

import com.homelab.interview_tracking_app.job_post.create_jobposts.dto.JobPostCreationRequest;
import com.homelab.interview_tracking_app.job_post.create_jobposts.dto.JobPostCreationResponse;
import com.homelab.interview_tracking_app.job_post.create_jobposts.service.JobPostCreationService;
import com.homelab.interview_tracking_app.shared.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jobposts")
@RequiredArgsConstructor
public class JobPostCreationController {
    private final JobPostCreationService jobPostCreationService;

    @PostMapping
    public ResponseEntity<ApiResponse<JobPostCreationResponse>> createJobPost(@RequestBody JobPostCreationRequest request) {
        JobPostCreationResponse response =
                jobPostCreationService.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));

    }

}
