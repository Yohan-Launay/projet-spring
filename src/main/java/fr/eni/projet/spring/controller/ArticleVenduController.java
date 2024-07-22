package fr.eni.projet.spring.controller;

import fr.eni.projet.spring.bll.articleVendu.ArticleVenduService;
import fr.eni.projet.spring.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;

    @GetMapping("/encheres")
    public String items(Model model) {
        List<ArticleVendu> items = articleVenduService.findAll();
        model.addAttribute("items", items);

        return "encheres";
    }
}
