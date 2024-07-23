package fr.eni.projet.spring.dal.articleVendu;

import fr.eni.projet.spring.bo.ArticleVendu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
        String sql = "SELECT a.no_article AS id_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, " +
                "u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, " +
                "c.no_categorie, c.libelle " +
                "FROM ARTICLES_VENDUS a " +
                "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie " +
                "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur";
        List<ArticleVendu> list = jdbcTemplate.query(sql, new ArticleVenduRowMapper());
        return list;
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
