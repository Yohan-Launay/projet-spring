package fr.eni.projet.spring.controller;

import fr.eni.projet.spring.bll.UtilisateurService;
import fr.eni.projet.spring.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/profile")
    public String myAccount(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        Utilisateur currentUtilisateur = utilisateurService.getUtilisateur(utilisateur.getNo_utilisateur());

        model.addAttribute("userData", currentUtilisateur);

        return "utilisateur/profile";
    }

    @GetMapping("/user-profile")
    public String userProfile(@RequestParam int id, Model model) {

        Utilisateur currentUtilisateur = utilisateurService.getUtilisateur(id);

        model.addAttribute("userData", currentUtilisateur);

        return "utilisateur/user-profile";
    }

    @GetMapping("/profile/update")
    public String getUpdateProfileForm(@RequestParam int id, Model model) {

        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);

        model.addAttribute("userData", utilisateur);

        return "utilisateur/update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute("userData") Utilisateur utilisateur, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "utilisateur/update";
        }

        utilisateurService.updateUtilisateur(utilisateur);

        return "redirect:/profile";
    }

    @GetMapping("/profile/delete")
    public String deleteProfile(@RequestParam int id) {
        utilisateurService.deleteUtilisateur(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur principal = (Utilisateur) auth.getPrincipal();

        return "redirect:/logout";
    }
}
