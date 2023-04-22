package co.istad.thymeleaf.webapp.controller;

import co.istad.thymeleaf.webapp.model.Article;
import co.istad.thymeleaf.webapp.model.Author;
import co.istad.thymeleaf.webapp.service.ArticleService;
import co.istad.thymeleaf.webapp.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping()
    String listingAuthor(Model model){
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "pages/author/authorList";
    }

    @GetMapping("/new")
    String getAuthor(Model model,Author author){
        model.addAttribute("authors", author);
        return "pages/author/author-new";
    }

    @PostMapping("/new")
    String doSaveAuthor(@ModelAttribute @Valid Author author,
                         BindingResult result,
                         @RequestParam("profile") MultipartFile file,
                         Model model) {

        /*if (result.hasErrors()) {
            model.addAttribute("author", author);
            return "pages/author/author-new";
        }*/

        boolean isSucceed = authorService.save(author, file);
        if (isSucceed) {
            return "redirect:/author";
        }

        return "pages/author/author-new";
    }
}
