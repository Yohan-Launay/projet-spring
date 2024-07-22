package fr.eni.projet.spring.bll.articleVendu;

import fr.eni.projet.spring.bo.ArticleVendu;
import fr.eni.projet.spring.dal.articleVendu.ArticleVenduRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    ArticleVenduRepository articleVenduRepository;

    @Override
    public List<ArticleVendu> findAll() {
        return articleVenduRepository.findAll();
    }

    @Override
    public ArticleVendu findById(int id) {
        return articleVenduRepository.findById(id);
    }

    @Override
    public void save(ArticleVendu articleVendu) {
        articleVenduRepository.save(articleVendu);
    }

    @Override
    public void deleteById(int id) {
        this.articleVenduRepository.deleteById(id);
    }

    @Override
    public void update(ArticleVendu articleVendu) {
        articleVenduRepository.save(articleVendu);
    }
}
