package com.homelab.interview_tracking_app.job_post.create_jobposts.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record JobPostCreationResponse(

        Integer id,
        String title,
        String url,

        @JsonProperty("company_name")
        String companyName
) {
}