package com.homelab.interview_tracking_app.shared.error_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String defaultMessage;
    }