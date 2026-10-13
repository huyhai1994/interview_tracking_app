package com.homelab.interview_tracking_app.job_post.create_jobposts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record JobPostCreationRequest(

        String title,

        @JsonProperty("apply_end_at")
        Instant applyEndAt,

        @JsonProperty("experience_min")
        Integer experienceMin,

        @JsonProperty("experience_max")
        Integer experienceMax,

        String url,

        @JsonProperty("company_id")
        Integer companyId,

        @JsonProperty("job_platform_id")
        Integer jobPlatformId
) {
}