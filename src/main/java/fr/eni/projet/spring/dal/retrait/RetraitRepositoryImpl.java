package fr.eni.projet.spring.dal.retrait;

import fr.eni.projet.spring.bo.Retrait;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RetraitRepositoryImpl implements RetraitRepository{
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RetraitRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Retrait> findAll() {
        return List.of();
    }

    @Override
    public Retrait findById(int id) {
        return null;
    }

    @Override
    public void save(Retrait retrait) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Retrait retrait) {

    }
}
