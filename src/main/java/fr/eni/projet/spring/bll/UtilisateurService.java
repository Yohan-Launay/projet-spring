package fr.eni.projet.spring.bll;

import fr.eni.projet.spring.bo.Role;
import fr.eni.projet.spring.bo.Utilisateur;
import fr.eni.projet.spring.dal.Utilisateur.UtilisateurRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private PasswordEncoder encoderBean;
    private UtilisateurRepository utilisateurRepository;
    private EnchereService enchereService;
    private ArticleVenduService articleVenduService;

    public UtilisateurService(PasswordEncoder encoderBean, UtilisateurRepository utilisateurRepository, EnchereService enchereService, ArticleVenduService articleVenduService, RetraitService retraitService) {
        this.utilisateurRepository = utilisateurRepository;
        this.enchereService = enchereService;
        this.articleVenduService = articleVenduService;
        this.encoderBean = encoderBean;
    }

    public void registerUtilisateur(Utilisateur utilisateur) {

        String password = utilisateur.getMot_de_passe();
        String encryptedPassword = encoderBean.encode(password);

        utilisateur.setMot_de_passe(encryptedPassword);
        utilisateur.setAdministrateur(false);
        utilisateur.setCredit(10);

        utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getUtilisateurs() {

        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateur(int id) {

        return utilisateurRepository.findById(id);
    }

    public Utilisateur getUtilisateurByEmail(String email) {

        return utilisateurRepository.findByEmail(email);
    }

    public void updateUtilisateur(Utilisateur utilisateur) {

        //Récupération de l'utilisateur connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur principal = (Utilisateur) auth.getPrincipal();

        //encodage nouveau mot de passe
        String encryptedPassword = encoderBean.encode(utilisateur.getMot_de_passe());
        utilisateur.setMot_de_passe(encryptedPassword);

        //Mise à jour de l'utilisateur si le mot de passe saisi est le mot de passe de l'utilisateur courant
//        if(encoderBean.matches(password,principal.getMot_de_passe()))
        utilisateurRepository.update(utilisateur);

        // On récupère l'utilisateur mis à jour
        Utilisateur updatedUser = getUtilisateur(utilisateur.getNo_utilisateur());

        // Si l'utilisateur mis à jour est l'utilisateur connecté, on met à jour le principal
        if(updatedUser.getNo_utilisateur() == principal.getNo_utilisateur()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(updatedUser, updatedUser.getPassword(), updatedUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public void deleteUtilisateur(int id) {

        enchereService.deleteEnchereByUserId(id);
        articleVenduService.deleteArticlesVenduByUserId(id);
        utilisateurRepository.deleteById(id);
    }

    public void changeCredit(int id,int credit) {
        utilisateurRepository.changeCredit(id, credit);
    }

    public List<Role> getUserRoles(String username){
        return utilisateurRepository.findRolesByUsername(username);
    }

//    public void desactivateUser(int id) {
//
//        enchereService.deleteEnchereByUserId(id);
//        articleVenduService.deleteArticlesVenduByUserId(id);
//        utilisateurRepository.desactivateById(id);
//    }

    public void activateUser(int id) {

        utilisateurRepository.activateById(id);
    }

    public void updatePassword(int id, String password) {

        String encodedPassword = encoderBean.encode(password);

        utilisateurRepository.saveNewPassword(id, encodedPassword);
    }

}
