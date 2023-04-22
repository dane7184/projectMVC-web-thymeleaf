package co.istad.thymeleaf.webapp.controller;

import co.istad.thymeleaf.webapp.model.Article;
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
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final AuthorService authorService;

    @GetMapping
    String viewArticle(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "pages/article/article";
    }
    @GetMapping("/new")
    String viewArticleNew(Article article,Model model){
        model.addAttribute("article", article);
        model.addAttribute("authorList", authorService.findAll());
        return "pages/article/article-new";
    }

    @PostMapping("/new")
    String doSaveArticle(@ModelAttribute @Valid Article article,
                         BindingResult result,
                         @RequestParam("thumbnailFile") MultipartFile file,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("article", article);
            return "pages/article/article-new";
        }

        boolean isSucceed = articleService.save(article, file);
        if (isSucceed) {
            return "redirect:/article";
        }

        return "pages/article/article-new";
    }

    @GetMapping("/category/{category}")
    String viewArticleByCategory(Model model, @PathVariable String category) {
        List<Article> articles = articleService.findArticleByCategory(category);
        model.addAttribute("articles", articles);
        return "pages/category/category";
    }
}
