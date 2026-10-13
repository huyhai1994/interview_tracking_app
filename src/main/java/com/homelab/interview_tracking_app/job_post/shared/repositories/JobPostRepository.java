package com.homelab.interview_tracking_app.job_post.shared.repositories;

import com.homelab.interview_tracking_app.job_post.shared.entities.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

    @Query("""
            SELECT jp
            FROM JobPost jp
            JOIN FETCH jp.company
            JOIN FETCH jp.jobPlatform
            WHERE jp.id = :id
            """)
    Optional<JobPost> findJobPostById(@Param("id") Integer id);
}
