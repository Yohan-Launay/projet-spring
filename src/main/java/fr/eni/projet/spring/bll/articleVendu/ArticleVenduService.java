package fr.eni.projet.spring.bll.articleVendu;

import fr.eni.projet.spring.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {
    List<ArticleVendu> findAll();
    ArticleVendu findById(int id);
    void save(ArticleVendu articleVendu);
    void deleteById(int id);
    void update(ArticleVendu articleVendu);
}
