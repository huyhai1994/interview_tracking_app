package com.homelab.interview_tracking_app;

import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class InterviewTrackingAppApplicationTests extends AbstractIntegrationTest {

    @Test
    void contextLoads() {
    }

}
