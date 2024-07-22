package fr.eni.projet.spring.dal.enchere;

import fr.eni.projet.spring.bo.Enchere;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnchereRowMapper implements RowMapper<Enchere> {
    @Override
    public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enchere enchere = new Enchere();

       enchere.setId_enchere(rs.getInt("id_enchere"));
       enchere.setNo_article(rs.getInt("no_article"));
       enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
       Date finEnchere = rs.getDate("date_enchere");
       enchere.setDate_enchere(finEnchere.toLocalDate());
       enchere.setMontant_enchere(rs.getInt("montant_enchere"));

       return enchere;
    }
}
