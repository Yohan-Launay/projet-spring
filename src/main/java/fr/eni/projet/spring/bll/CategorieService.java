package fr.eni.projet.spring.bll;

import fr.eni.projet.spring.bo.Categorie;
import fr.eni.projet.spring.dal.categorie.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorie(int id) {
        return categorieRepository.findById(id);
    }
}
