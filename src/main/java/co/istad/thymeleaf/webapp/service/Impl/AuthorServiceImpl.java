package co.istad.thymeleaf.webapp.service.Impl;

import co.istad.thymeleaf.webapp.model.Author;
import co.istad.thymeleaf.webapp.model.FileUpload;
import co.istad.thymeleaf.webapp.repository.StaticAuthorRepository;
import co.istad.thymeleaf.webapp.service.AuthorService;
import co.istad.thymeleaf.webapp.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final StaticAuthorRepository authorRepository;
    private final FileUploadService fileUploadService;

    @Override
    public List<Author> findAll() {

        return authorRepository.authors;
    }

    @Override
    public boolean save(Author author, MultipartFile file) {
        FileUpload fileUpload = fileUploadService.uploadSingle(file);

        if (fileUpload.isSucceed()) {
            author.setId(UUID.randomUUID().toString());
            author.setProfile(fileUpload.fileName());
            authorRepository.authors.add(author);
        }
        return true;
    }

    @Override
    public Author getAuthorById(String id) {
        for (Author author : authorRepository.authors) {
            if (author.getId().equalsIgnoreCase(id)) {
                return author;
            }
        }
        return null;
    }
}
