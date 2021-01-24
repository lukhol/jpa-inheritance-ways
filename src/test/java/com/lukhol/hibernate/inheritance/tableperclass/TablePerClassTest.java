package com.lukhol.hibernate.inheritance.tableperclass;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TablePerClassTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserAggregatorRepository userAggregatorRepository;

    @Test
    public void subClassesInOwnTables_aggregateCanFetchBothAsSuperClass() {
        // Given
        prepare();

        // When
        List<UserAggregator> aggregators = userAggregatorRepository.findAll();

        // Then
        assertThat(aggregators).hasSize(1);
        UserAggregator aggregator = aggregators.get(0);
        assertThat(aggregator.getUsers()).hasSize(2);
        assertThat(aggregator.getUsers().get(0).getClass()).isNotEqualTo(aggregator.getUsers().get(1).getClass());
    }

    private void prepare() {
        Customer customer = new Customer();
        customer.setExternalId("123");
        customer.setEmail("customer@email.com");

        Admin admin = new Admin();
        admin.setName("admin");
        admin.setEmail("admin@email.com");

        UserAggregator aggregator = new UserAggregator();
        aggregator.addUser(customer);
        aggregator.addUser(admin);

        entityManager.unwrap(Session.class).save(customer);
        entityManager.unwrap(Session.class).save(admin);
        entityManager.unwrap(Session.class).save(aggregator);
        entityManager.flush();
    }
}
