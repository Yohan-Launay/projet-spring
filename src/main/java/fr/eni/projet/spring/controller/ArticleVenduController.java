package fr.eni.projet.spring.controller;

import fr.eni.projet.spring.bll.*;
import fr.eni.projet.spring.bo.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private RetraitService retraitService;

    public ArticleVenduController(RetraitService retraitService, ArticleVenduService articleVenduService, CategorieService categorieService, EnchereService enchereService, UtilisateurService utilisateurService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
        this.utilisateurService = utilisateurService;
        this.retraitService = retraitService;
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

    @GetMapping("/article")
    public String showDetails(@RequestParam("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur CurrentUtilisateur = (Utilisateur) auth.getPrincipal();
        CurrentUtilisateur = utilisateurService.getUtilisateur(CurrentUtilisateur.getNo_utilisateur());
        ArticleVendu article = articleVenduService.getArticleVendu(id);
        Enchere enchere = enchereService.getEnchere(id);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(article.getUtilisateur().getNo_utilisateur());
        Retrait retrait = retraitService.getRetrait(article.getNo_article());

        if (enchere != null) {
            Utilisateur enchereUtilisateur = utilisateurService.getUtilisateur(enchere.getNo_utilisateur());
            model.addAttribute("enchereUtilisateur", enchereUtilisateur);
            model.addAttribute("enchere", enchere);
        } else {
            model.addAttribute("enchereUtilisateur", null);
            Enchere enchere_vide = new Enchere();
            enchere_vide.setMontant_enchere(article.getPrix_initial());
            model.addAttribute("enchere", enchere_vide);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Map<String, String> formattedDates = new HashMap<>();
        String formattedDateDebut = article.getDate_debut_encheres().format(formatter);
        String formattedDateFin = article.getDate_fin_encheres().format(formatter);

        // Utiliser des clés combinées pour stocker les dates de début et de fin
        formattedDates.put(article.getNo_article() + "_debut", formattedDateDebut);
        formattedDates.put(article.getNo_article() + "_fin", formattedDateFin);


        model.addAttribute("formattedDates", formattedDates);
        model.addAttribute("retrait", retrait);
        model.addAttribute("article", article);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("CurrentUtilisateur", CurrentUtilisateur);

        return "article/article";
    }

    @GetMapping("article/add")
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
        return "article/add";
    }

    @PostMapping("article/add")
    public String addArticle(@ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("retrait") Retrait retrait) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleVendu.setUtilisateur(utilisateur);
        articleVenduService.addArticleVendu(articleVendu);

        retrait.setNo_article(articleVendu.getNo_article());
        retraitService.addRetrait(retrait);

        return "redirect:/article?id=" + articleVendu.getNo_article();
    }
}
