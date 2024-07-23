package fr.eni.projet.spring.bll;

import fr.eni.projet.spring.bo.Retrait;
import fr.eni.projet.spring.dal.retrait.RetraitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitService {

    private RetraitRepository retraitRepository;

    public RetraitService(RetraitRepository retraitRepository) {
        this.retraitRepository = retraitRepository;
    }

    public List<Retrait> getAllRetrait() {
        return retraitRepository.findAll();
    }

    public Retrait getRetrait(int id) {
        return retraitRepository.findById(id);
    }

    public void addRetrait(Retrait retrait) {
        retraitRepository.save(retrait);
    }

    public void updateRetrait(Retrait retrait) {
        retraitRepository.update(retrait);
    }

    public void deleteRetrait(int id) {
        retraitRepository.deleteById(id);
    }
}
