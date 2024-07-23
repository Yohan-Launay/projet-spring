package fr.eni.projet.spring.dal.Utilisateur;


import fr.eni.projet.spring.bo.Role;
import fr.eni.projet.spring.bo.Utilisateur;
import fr.eni.projet.spring.dal.Dao;

import java.util.List;

public interface UtilisateurRepository extends Dao<Utilisateur> {

    Utilisateur findByEmail(String email);
    void changeCredit(int id, int credit);
    List<Role> findRolesByUsername(String username);
    void desactivateById(int id);
    void activateById(int id);
    void saveNewPassword(int id, String password);

}
