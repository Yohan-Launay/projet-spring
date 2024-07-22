package fr.eni.projet.spring.bll.enchere;

import fr.eni.projet.spring.bo.Enchere;

import java.util.List;

public interface EnchereService {
    List<Enchere> getAll();
    Enchere getById(int id);
    void save(Enchere enchere);
    void deleteById(int id);
    void update(Enchere enchere);
}
