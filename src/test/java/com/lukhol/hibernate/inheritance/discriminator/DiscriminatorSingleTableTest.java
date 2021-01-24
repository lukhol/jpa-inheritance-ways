package com.lukhol.hibernate.inheritance.discriminator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DiscriminatorSingleTableTest {

    @Autowired
    private PushMessageHistoryRepository pushMessageHistoryRepository;

    @Autowired
    private MobileApplicationDao mobileApplicationDao;

    @Test
    public void childrenClassCanBeSetOnHistoryEntity() {
        // Given
        CustomerMobileApplication customerMobileApplication = new CustomerMobileApplication();
        customerMobileApplication.setUserId("1");
        customerMobileApplication.setToken("toke-admin-mobile-app-2");
        mobileApplicationDao.save(customerMobileApplication);

        AdminMobileApplication adminMobileApplication = new AdminMobileApplication();
        adminMobileApplication.setCustomerId("2");
        adminMobileApplication.setToken("toke-admin-mobile-app-1");
        mobileApplicationDao.save(adminMobileApplication);

        PushMessageHistory messageHistory = new PushMessageHistory();
        messageHistory.setApplication(customerMobileApplication);
        messageHistory.setTitle("Some title");
        messageHistory.setApplication(adminMobileApplication);

        PushMessageHistory messageHistory2 = new PushMessageHistory();
        messageHistory2.setApplication(adminMobileApplication);
        messageHistory2.setTitle("Some title 2");
        messageHistory2.setApplication(customerMobileApplication);

        pushMessageHistoryRepository.save(messageHistory);
        pushMessageHistoryRepository.save(messageHistory2);

        // When
        List<PushMessageHistory> historyList = pushMessageHistoryRepository.findAll();

        // Then
        assertThat(historyList).hasSize(2);
        assertThat(historyList.get(0).getApplication().getClass()).isNotEqualTo(historyList.get(1).getApplication().getClass());
    }
}
