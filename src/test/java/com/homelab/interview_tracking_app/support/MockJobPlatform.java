package com.homelab.interview_tracking_app.support;

import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;

import java.util.List;

public final class MockJobPlatform {

    private MockJobPlatform() {
    }

    public static JobPlatform create(String name) {
        JobPlatform platform = new JobPlatform();
        platform.setName(name);
        return platform;
    }

    public static JobPlatform topCV() {
        return create("TopCV");
    }

    public static JobPlatform vietnamWorks() {
        return create("VietnamWorks");
    }

    public static JobPlatform linkedin() {
        return create("LinkedIn");
    }

    public static JobPlatform itviec() {
        return create("ITviec");
    }

    public static JobPlatform careerViet() {
        return create("CareerViet");
    }

    public static List<JobPlatform> all() {
        return List.of(
                topCV(),
                vietnamWorks(),
                linkedin(),
                itviec(),
                careerViet()
        );
    }
}