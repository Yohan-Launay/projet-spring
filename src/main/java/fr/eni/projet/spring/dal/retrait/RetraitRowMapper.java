package fr.eni.projet.spring.dal.retrait;

import fr.eni.projet.spring.bo.Retrait;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RetraitRowMapper implements RowMapper<Retrait> {
    public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
        Retrait retrait = new Retrait();

        retrait.setNo_article(rs.getInt("no_article"));
        retrait.setRue(rs.getString("rue"));
        retrait.setCode_postal(rs.getString("code_postal"));
        retrait.setVille(rs.getString("ville"));

        return retrait;
    }
}
