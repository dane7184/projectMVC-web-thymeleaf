package co.istad.thymeleaf.webapp.service;

import co.istad.thymeleaf.webapp.model.Article;
import co.istad.thymeleaf.webapp.model.Author;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    boolean save(Author author, MultipartFile file);

    Author getAuthorById(String id);
}
