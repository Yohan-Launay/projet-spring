package fr.eni.projet.spring.bll;

import fr.eni.projet.spring.bo.Enchere;
import fr.eni.projet.spring.dal.enchere.EnchereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereService {

    private EnchereRepository enchereRepository;

    public EnchereService(EnchereRepository enchereRepository) {

        this.enchereRepository = enchereRepository;
    }

    public List<Enchere> getAllEnchere() {

        return enchereRepository.findAll();
    }

    public Enchere getEnchere(int id) {

        return enchereRepository.findById(id);
    }

    public List<Enchere> getEnchereByArticle(int articleId) {

        return enchereRepository.findByIdArticle(articleId);
    }

    public List<Enchere> getEnchereByUtilisateur(int utilisateurId) {
        return enchereRepository.findByIdUtilisateur(utilisateurId);
    }

    public void addEnchere(Enchere enchere) {

        enchereRepository.save(enchere);
    }

    public void updateEnchere(Enchere enchere) {

        enchereRepository.update(enchere);
    }

    public void deleteEnchere(int id) {

        enchereRepository.deleteById(id);
    }

    public void deleteEnchereByUserId(int userId) {

        enchereRepository.deleteByIdUtilisateur(userId);
    }
}
