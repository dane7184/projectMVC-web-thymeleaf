package co.istad.thymeleaf.webapp.service.Impl;

import co.istad.thymeleaf.webapp.model.Article;
import co.istad.thymeleaf.webapp.model.FileUpload;
import co.istad.thymeleaf.webapp.repository.StaticRepository;
import co.istad.thymeleaf.webapp.service.ArticleService;
import co.istad.thymeleaf.webapp.service.AuthorService;
import co.istad.thymeleaf.webapp.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final StaticRepository staticRepository;
    private final FileUploadService fileUploadService;
    private final AuthorService authorService;


    @Override
    public List<Article> findAll() {
        return staticRepository.getArticles();
    }

    @Override
    public boolean save(Article article, MultipartFile file) {
        FileUpload fileUpload = fileUploadService.uploadSingle(file);
        if (fileUpload.isSucceed()){
            article.setUuid(UUID.randomUUID());
            article.setThumbnail(fileUpload.fileName());
            article.setIndex(staticRepository.getArticles().size());
            article.setAuthor(authorService.getAuthorById(article.getAuthor().getId()));
            staticRepository.getArticles().add(article);
        }
        return true;
    }

    @Override
    public Article getArticleByIndex(int ind) {

        return staticRepository.getArticles().get(ind);
    }

    @Override
    public List<Article> findArticleByAuthorId(String authorId) {
        List<Article> articles = new ArrayList<>();
        for (Article article : staticRepository.getArticles()){
            if (article.getAuthor().getId().equalsIgnoreCase(authorId)){
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public List<Article> findArticleByCategory(String category) {
        List<Article> articles = new ArrayList<>();
        for (Article article : staticRepository.getArticles()){
            if (article.getCategory().equalsIgnoreCase(category)){
                articles.add(article);
            }
        }
        return articles;
    }
}
