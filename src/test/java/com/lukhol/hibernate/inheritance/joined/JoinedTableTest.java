package com.lukhol.hibernate.inheritance.joined;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JoinedTableTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        // Given
        prepare();

        // When
        List<Library> libraryList = libraryRepository.findAll();

        // Then
        Library library = libraryList.get(0);
        assertThat(library.getPublications()).hasSize(2);
    }

    private void prepare() {
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
}
