package co.istad.thymeleaf.webapp.controller;

import co.istad.thymeleaf.webapp.model.Article;
import co.istad.thymeleaf.webapp.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final ArticleService articleService;

    @GetMapping("/profileCard/{ind}")
    String viewProfile(@PathVariable int ind, Model model){
        Article article = articleService.getArticleByIndex(ind);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("thumbnail", article.getThumbnail());
        model.addAttribute("author", article.getAuthor());
        model.addAttribute("text", article.getText());
        model.addAttribute("articleList", articleService.findArticleByAuthorId(article.getAuthor().getId()));
        return "pages/profileCard";
    }
}
