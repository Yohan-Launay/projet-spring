package fr.eni.projet.spring.dal.categorie;

import fr.eni.projet.spring.bo.ArticleVendu;
import fr.eni.projet.spring.bo.Categorie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieRepositoryImpl implements CategorieRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategorieRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Categorie> findAll() {
        return List.of();
    }

    @Override
    public Categorie findById(int id) {
        return null;
    }

    @Override
    public void save(Categorie categorie) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Categorie categorie) {

    }
}
