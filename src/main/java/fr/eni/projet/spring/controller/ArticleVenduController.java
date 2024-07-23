package fr.eni.projet.spring.controller;

import fr.eni.projet.spring.bll.ArticleVenduService;
import fr.eni.projet.spring.bll.CategorieService;
import fr.eni.projet.spring.bll.EnchereService;
import fr.eni.projet.spring.bll.UtilisateurService;
import fr.eni.projet.spring.bo.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;
    private EnchereService enchereService;
    private UtilisateurService utilisateurService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService, EnchereService enchereService, UtilisateurService utilisateurService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/encheres")
    public String items(Model model) {
        List<ArticleVendu> listArticles = articleVenduService.getArticleVendu();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Map<String, String> formattedDates = new HashMap<>();

        for (ArticleVendu articleVendu : listArticles) {
            Enchere enchere = enchereService.getEnchere(articleVendu.getNo_article());
            if (enchere != null) {
                articleVendu.setPrix_initial(enchere.getMontant_enchere());
            }
            String formattedDateDebut = articleVendu.getDate_debut_encheres().format(formatter);
            String formattedDateFin = articleVendu.getDate_fin_encheres().format(formatter);

            // Utiliser des clés combinées pour stocker les dates de début et de fin
            formattedDates.put(articleVendu.getNo_article() + "_debut", formattedDateDebut);
            formattedDates.put(articleVendu.getNo_article() + "_fin", formattedDateFin);
        }

        model.addAttribute("articles", listArticles);
        model.addAttribute("formattedDates", formattedDates);

        return "encheres";
    }

    @GetMapping("/add-article")
    public String addArticle(Model model){
        ArticleVendu articleVendu = new ArticleVendu();
        List<Categorie> listCat = categorieService.getAllCategories();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        utilisateur = utilisateurService.getUtilisateur(utilisateur.getNo_utilisateur());
        Retrait retrait = new Retrait();

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("categories", listCat);
        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("retrait", retrait);
        return "add-article";
    }
}
