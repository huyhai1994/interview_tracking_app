-- ==========================================
-- 1. COMPANIES
-- ==========================================
CREATE TABLE IF NOT EXISTS companies
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    website VARCHAR(2048),

    PRIMARY KEY (id)
) ENGINE = InnoDB;


-- ==========================================
-- 2. JOB PLATFORM
-- ==========================================
CREATE TABLE IF NOT EXISTS job_platform
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_job_platform_name UNIQUE (name)
) ENGINE = InnoDB;


-- ==========================================
-- 3. APPLY MEDIA
-- ==========================================
CREATE TABLE IF NOT EXISTS apply_media
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_apply_media_name UNIQUE (name)
) ENGINE = InnoDB;


-- ==========================================
-- 4. JOB POSTS
-- ==========================================
CREATE TABLE IF NOT EXISTS job_posts
(
    id              INT UNSIGNED NOT NULL AUTO_INCREMENT,

    title           VARCHAR(255) NOT NULL,
    apply_end_at    DATETIME,
    experience_min  TINYINT UNSIGNED,
    experience_max  TINYINT UNSIGNED,
    url             VARCHAR(2048),

    company_id      INT UNSIGNED NOT NULL,
    job_platform_id INT UNSIGNED,

    created_at      DATETIME     NOT NULL
        DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_job_posts_company
        FOREIGN KEY (company_id)
            REFERENCES companies (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,

    CONSTRAINT fk_job_posts_platform
        FOREIGN KEY (job_platform_id)
            REFERENCES job_platform (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,

    CONSTRAINT chk_job_posts_experience
        CHECK (
            experience_min IS NULL
                OR experience_max IS NULL
                OR experience_min <= experience_max
            )
) ENGINE = InnoDB;


-- ==========================================
-- 5. JOB APPLY
-- ==========================================
CREATE TABLE IF NOT EXISTS job_apply
(
    id             INT UNSIGNED NOT NULL AUTO_INCREMENT,

    job_post_id    INT UNSIGNED NOT NULL,
    apply_media_id INT UNSIGNED,

    apply_at       DATETIME     NOT NULL,

    status         VARCHAR(30)  NOT NULL,

    created_at     DATETIME     NOT NULL
        DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME     NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_job_apply_post
        FOREIGN KEY (job_post_id)
            REFERENCES job_posts (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,

    CONSTRAINT fk_job_apply_media
        FOREIGN KEY (apply_media_id)
            REFERENCES apply_media (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT
) ENGINE = InnoDB;


-- ==========================================
-- 6. INTERVIEW
-- ==========================================
CREATE TABLE IF NOT EXISTS interview
(
    id           INT UNSIGNED     NOT NULL AUTO_INCREMENT,

    job_apply_id INT UNSIGNED     NOT NULL,

    name         VARCHAR(255)     NOT NULL,
    round_no     TINYINT UNSIGNED NOT NULL,
    interview_at DATETIME,

    status       VARCHAR(30)      NOT NULL,

    created_at   DATETIME         NOT NULL
        DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME         NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_interview_job_apply
        FOREIGN KEY (job_apply_id)
            REFERENCES job_apply (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,

    CONSTRAINT chk_interview_round
        CHECK (round_no > 0)
) ENGINE = InnoDB;


-- ==========================================
-- 7. QUESTIONS
-- ==========================================
CREATE TABLE IF NOT EXISTS questions
(
    id              INT UNSIGNED NOT NULL AUTO_INCREMENT,

    interview_id    INT UNSIGNED NOT NULL,
    question_doc_id VARCHAR(255) NOT NULL,

    created_at      DATETIME     NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT uk_questions_interview_doc
        UNIQUE (interview_id, question_doc_id),

    CONSTRAINT fk_questions_interview
        FOREIGN KEY (interview_id)
            REFERENCES interview (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT
) ENGINE = InnoDB;
