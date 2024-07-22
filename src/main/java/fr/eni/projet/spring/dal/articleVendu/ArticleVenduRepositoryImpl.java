package fr.eni.projet.spring.dal.articleVendu;

import fr.eni.projet.spring.bo.ArticleVendu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

@Repository
public class ArticleVenduRepositoryImpl implements ArticleVenduRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ArticleVenduRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public void deleteByIdUtilisateur(int idUtilisateur) {

    }

    @Override
    public List<ArticleVendu> getArticlesVenduByUser(int idUtilisateur) {
        return List.of();
    }

    @Override
    public List<ArticleVendu> findAll() {
        return List.of();
    }

    @Override
    public ArticleVendu findById(int id) {
        return null;
    }

    @Override
    public void save(ArticleVendu articleVendu) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(ArticleVendu articleVendu) {

    }
}
