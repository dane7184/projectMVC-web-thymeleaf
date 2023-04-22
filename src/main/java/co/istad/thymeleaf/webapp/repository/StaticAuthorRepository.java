package co.istad.thymeleaf.webapp.repository;

import co.istad.thymeleaf.webapp.model.Article;
import co.istad.thymeleaf.webapp.model.Author;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Getter
public class StaticAuthorRepository {
    private Faker faker;

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public List<Author> authors;

    @PostConstruct
    void init() {
        authors = new ArrayList<>() {{
            add(new Author(UUID.randomUUID().toString(), faker.book().author(), "p01.jpg"));
            add(new Author(UUID.randomUUID().toString(), faker.book().author(), "p02.jpg"));
            add(new Author(UUID.randomUUID().toString(), faker.book().author(), "love.jpg"));
            add(new Author(UUID.randomUUID().toString(), faker.book().author(), "love02.png"));
        }};
    }
}
