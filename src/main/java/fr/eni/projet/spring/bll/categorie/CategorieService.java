package fr.eni.projet.spring.bll.categorie;

import fr.eni.projet.spring.bo.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> findAll();
    Categorie findById(int id);
    void save(Categorie categorie);
    void deleteById(int id);
    void update(Categorie categorie);
}
