package fr.eni.projet.spring.bll.enchere;

import fr.eni.projet.spring.bo.Enchere;
import fr.eni.projet.spring.dal.enchere.EnchereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {

    EnchereRepository enchereRepository;

    @Override
    public List<Enchere> getAll() {
        return enchereRepository.findAll();
    }

    @Override
    public Enchere getById(int id) {
        return enchereRepository.findById(id);
    }

    @Override
    public void save(Enchere enchere) {
        enchereRepository.save(enchere);
    }

    @Override
    public void deleteById(int id) {
        this.enchereRepository.deleteById(id);
    }

    @Override
    public void update(Enchere enchere) {
        enchereRepository.save(enchere);
    }
}
