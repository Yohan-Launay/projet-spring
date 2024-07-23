package fr.eni.projet.spring.controller;

import fr.eni.projet.spring.bll.UtilisateurService;
import fr.eni.projet.spring.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String utilisateur(Model model) {
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "register";
    }

    @PostMapping("/register")
    public String signin(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        utilisateurService.registerUtilisateur(utilisateur);
        return "redirect:/login";

    }
}
