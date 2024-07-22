package fr.eni.projet.spring.bll.retrait;

import fr.eni.projet.spring.bo.Retrait;
import fr.eni.projet.spring.dal.retrait.RetraitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitServiceImpl implements RetraitService {

    RetraitRepository retraitRepository;

    @Override
    public List<Retrait> findAll() {
        return retraitRepository.findAll();
    }

    @Override
    public Retrait findById(int id) {
        return retraitRepository.findById(id);
    }

    @Override
    public void save(Retrait retrait) {
        retraitRepository.save(retrait);
    }

    @Override
    public void deleteById(int id) {
        this.retraitRepository.deleteById(id);
    }

    @Override
    public void update(Retrait retrait) {
        retraitRepository.save(retrait);
    }
}
