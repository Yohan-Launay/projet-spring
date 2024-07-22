package fr.eni.projet.spring.dal.enchere;

import fr.eni.projet.spring.bo.Enchere;
import fr.eni.projet.spring.dal.Dao;

import java.util.List;

public interface EnchereRepository extends Dao<Enchere> {
    List<Enchere> findByIdUtilisateur(int idUtilisateur);
    List<Enchere> findByIdArticle(int idArticle);
    void deleteByIdUtilisateur(int idUtilisateur);
}
