package com.lukhol.hibernate.inheritance;

import com.lukhol.hibernate.inheritance.discriminator.*;
import com.lukhol.hibernate.inheritance.joined.BlogPost;
import com.lukhol.hibernate.inheritance.joined.Book;
import com.lukhol.hibernate.inheritance.joined.Library;
import com.lukhol.hibernate.inheritance.tableperclass.Admin;
import com.lukhol.hibernate.inheritance.tableperclass.Customer;
import com.lukhol.hibernate.inheritance.tableperclass.UserAggregator;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final PushMessageHistoryRepository pushMessageHistoryRepository;
    private final MobileApplicationDao mobileApplicationDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void prepareDb() {
        prepareJoined();
        prepareTablePerClass();
        prepareDiscriminator();
    }

    private void prepareDiscriminator() {
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
    }

    private void prepareJoined() {
        Book book = new Book();
        book.setIsbn("some-isbn");
        book.setPages(123);
        book.setTitle("Book title");

        BlogPost blogPost = new BlogPost();
        blogPost.setUrl("http://www.someurl/someblogpost");
        blogPost.setTitle("Blog Post title");

        Library library = new Library();
        library.addPublication(book);
        library.addPublication(blogPost);

        entityManager.unwrap(Session.class).save(book);
        entityManager.unwrap(Session.class).save(blogPost);
        entityManager.unwrap(Session.class).save(library);
        entityManager.flush();
    }

    private void prepareTablePerClass() {
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
