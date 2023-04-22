package co.istad.thymeleaf.webapp.repository;

import co.istad.thymeleaf.webapp.model.Article;
import co.istad.thymeleaf.webapp.service.AuthorService;
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
public class StaticRepository {

    private Faker faker;
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    private List<Article> articles;

    @PostConstruct
    void init() {
        articles = new ArrayList<>() {{
            add(new Article(UUID.randomUUID(), faker.book().title(), "p01.jpg", authorService.findAll().get(0), faker.book().toString(), 0, "Game"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p03.jpg", authorService.findAll().get(1), faker.book().genre(), 1, "Love"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p02.jpg", authorService.findAll().get(2), faker.book().genre(), 2, "Study"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p01.jpg", authorService.findAll().get(3), faker.book().genre(), 3, "Game"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p02.jpg", authorService.findAll().get(1), faker.book().genre(), 4, "Music"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p03.jpg", authorService.findAll().get(2), faker.book().genre(), 5, "Love"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p01.jpg", authorService.findAll().get(3), faker.book().genre(), 6, "Game"));
            add(new Article(UUID.randomUUID(), faker.book().title(), "p02.jpg", authorService.findAll().get(0), faker.book().genre(), 7, "Music"));
        }};
    }

}