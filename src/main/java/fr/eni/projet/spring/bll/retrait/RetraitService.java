package fr.eni.projet.spring.bll.retrait;

import fr.eni.projet.spring.bo.Retrait;

import java.util.List;

public interface RetraitService {
    List<Retrait> findAll();
    Retrait findById(int id);
    void save(Retrait retrait);
    void deleteById(int id);
    void update(Retrait retrait);
}
