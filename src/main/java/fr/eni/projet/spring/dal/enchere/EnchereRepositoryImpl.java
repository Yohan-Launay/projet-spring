package fr.eni.projet.spring.dal.Enchere;

import fr.eni.projet.spring.bo.Enchere;
import fr.eni.projet.spring.dal.enchere.EnchereRepository;
import fr.eni.projet.spring.dal.enchere.EnchereRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
    public List<Enchere> findAll() {

        String sql = "select * from ENCHERES";
        List<Enchere> list = jdbcTemplate.query(sql, new EnchereRowMapper());
        return list;
    }

    @Override
    public Enchere findById(int id) {
        String sql = "SELECT * FROM ENCHERES WHERE no_article = :id AND montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = :id);";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        try {
            Enchere enchere =  namedParameterJdbcTemplate.queryForObject(sql, map, new EnchereRowMapper());
            return enchere;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public List<Enchere> findByIdUtilisateur(int idUtilisateur) {
        String sql = "select * from ENCHERES where no_utilisateur = :idUtilisateur;";
        List<Enchere> list = jdbcTemplate.query(sql, new EnchereRowMapper(), idUtilisateur);
        return list;
    }

    @Override
    public List<Enchere> findByIdArticle(int idArticle) {
        String sql = "select * from ENCHERES where no_article = :idArticle;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idArticle", idArticle);
        try{
            List<Enchere> list = namedParameterJdbcTemplate.query(sql, map, new EnchereRowMapper());
            return list;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void save(Enchere enchere) {
        String sql = "insert into ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (:no_utilisateur, :no_article, :date_enchere, :montant_enchere);";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_utilisateur", enchere.getNo_utilisateur());
        map.addValue("no_article", enchere.getNo_article());
        map.addValue("date_enchere", enchere.getDate_enchere());
        map.addValue("montant_enchere", enchere.getMontant_enchere());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from ENCHERES where no_article = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void deleteByIdUtilisateur(int idUtilisateur) {

        String sql = "delete from ENCHERES where no_utilisateur = :id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", idUtilisateur);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void update(Enchere enchere) {
        String sql = "update ENCHERES set no_utilisateur = :no_utilisateur, no_article = :no_article, date_enchere = :date_enchere, montant_enchere = :montant_enchere where no_article = :no_article";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_utilisateur", enchere.getNo_utilisateur());
        map.addValue("no_article", enchere.getNo_article());
        map.addValue("date_enchere", enchere.getDate_enchere());
        map.addValue("montant_enchere", enchere.getMontant_enchere());
        namedParameterJdbcTemplate.update(sql, map);
    }


}
