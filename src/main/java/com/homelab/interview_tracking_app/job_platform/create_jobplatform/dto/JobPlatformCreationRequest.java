package com.homelab.interview_tracking_app.job_platform.create_jobplatform.dto;


import lombok.NonNull;

public record JobPlatformCreationRequest(
        @NonNull String name
) {
}
