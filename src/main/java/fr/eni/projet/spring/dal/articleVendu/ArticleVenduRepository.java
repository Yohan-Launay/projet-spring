package fr.eni.projet.spring.dal.articleVendu;

import fr.eni.projet.spring.bo.ArticleVendu;
import fr.eni.projet.spring.dal.Dao;

import java.util.List;

public interface ArticleVenduRepository extends Dao<ArticleVendu> {
    void deleteByIdUtilisateur(int idUtilisateur);
    List<ArticleVendu> getArticlesVenduByUser(int idUtilisateur);
}
