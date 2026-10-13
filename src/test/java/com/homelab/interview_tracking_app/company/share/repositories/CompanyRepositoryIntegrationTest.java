package com.homelab.interview_tracking_app.company.share.repositories;

import com.homelab.interview_tracking_app.support.AbstractIntegrationTest;
import com.homelab.interview_tracking_app.support.MockCompany;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CompanyRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void save_whenAnNewCompanySavedSuccess_thenCouldQueryIt() {
        Integer id = companyRepository.save(MockCompany.samsung()).getId();
        entityManager.clear();
        assertThat(companyRepository.findById(id).orElseThrow()).isNotNull();
    }

}