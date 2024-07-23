package fr.eni.projet.spring.controller;

import fr.eni.projet.spring.bll.ArticleVenduService;
import fr.eni.projet.spring.bll.CategorieService;
import fr.eni.projet.spring.bo.ArticleVendu;
import fr.eni.projet.spring.bo.Categorie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
    }

    @GetMapping("/encheres")
    public String items(Model model) {
        List<ArticleVendu> items = articleVenduService.getArticleVendu();
        System.out.println("items: " + items);
        model.addAttribute("items", items);

        return "encheres";
    }
}
