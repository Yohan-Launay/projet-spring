package fr.eni.projet.spring.dal.enchere;

import fr.eni.projet.spring.bo.Enchere;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereRepositoryImpl implements EnchereRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EnchereRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Enchere> findByIdUtilisateur(int idUtilisateur) {
        return List.of();
    }

    @Override
    public List<Enchere> findByIdArticle(int idArticle) {
        return List.of();
    }

    @Override
    public void deleteByIdUtilisateur(int idUtilisateur) {

    }

    @Override
    public List<Enchere> findAll() {
        return List.of();
    }

    @Override
    public Enchere findById(int id) {
        return null;
    }

    @Override
    public void save(Enchere enchere) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Enchere enchere) {

    }
}
