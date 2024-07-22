package fr.eni.projet.spring.bll.categorie;

import fr.eni.projet.spring.bo.Categorie;
import fr.eni.projet.spring.dal.categorie.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    CategorieRepository categorieRepository;

    @Override
    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie findById(int id) {
        return categorieRepository.findById(id);
    }

    @Override
    public void save(Categorie categorie) {

    }

    @Override
    public void deleteById(int id) {
        this.categorieRepository.deleteById(id);
    }

    @Override
    public void update(Categorie categorie) {
        categorieRepository.save(categorie);
    }
}
