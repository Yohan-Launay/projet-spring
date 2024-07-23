package fr.eni.projet.spring.dal.articleVendu;

import fr.eni.projet.spring.bo.ArticleVendu;
import fr.eni.projet.spring.bo.Categorie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {
    @Override
    public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {

        ArticleVendu articleVendu = new ArticleVendu();

        articleVendu.setNo_article(rs.getInt("id_article"));
        articleVendu.setNom_article(rs.getString("nom_article"));
        articleVendu.setDescription(rs.getString("description"));
        Date debutEnchere = rs.getDate("date_debut_encheres");
        articleVendu.setDate_debut_encheres(debutEnchere.toLocalDate());
        Date finEnchere = rs.getDate("date_fin_encheres");
        articleVendu.setDate_fin_encheres(finEnchere.toLocalDate());
        articleVendu.setPrix_initial(rs.getInt("prix_initial"));
        articleVendu.setPrix_vente(rs.getInt("prix_vente"));

        Categorie categorie = new Categorie();
        categorie.setNo_categorie(rs.getInt("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));

        articleVendu.setCategorie(categorie);

        return articleVendu;
    }
}
