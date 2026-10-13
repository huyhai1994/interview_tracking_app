package com.homelab.interview_tracking_app.job_post.shared.entities;

import com.homelab.interview_tracking_app.company.share.entities.Company;
import com.homelab.interview_tracking_app.job_platform.shared.entity.JobPlatform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "job_posts")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "apply_end_at")
    private LocalDateTime applyEndAt;

    @JdbcTypeCode(SqlTypes.TINYINT)
    @Column(name = "experience_min")
    private Integer experienceMin;

    @JdbcTypeCode(SqlTypes.TINYINT)
    @Column(name = "experience_max")
    private Integer experienceMax;

    @Column(name = "url", length = 2048)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "company_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_job_posts_company")
    )
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "job_platform_id",
            foreignKey = @ForeignKey(name = "fk_job_posts_platform")
    )
    private JobPlatform jobPlatform;

    @Column(
            name = "created_at",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime updatedAt;
}