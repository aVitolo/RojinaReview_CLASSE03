package model.dao;

import model.beans.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {

    public Prodotto doRetriveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM prodotto WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Prodotto p = new Prodotto();
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzo(rs.getFloat(4)); //prezzo colonna 5?
                p.setDisponibilità(rs.getInt(6)); //disponibilità colonna 4?
                p.setCategorie(new CategoriaDAO().doRetrieveByProductId(id));
                p.setSconto(null);

                if(rs.getInt(6)==1){ //manca new Sconto() ?
                    ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    p.getSconto().setNome(rs.getString(1));
                    p.getSconto().setPercentuale(rs.getFloat(2));
                }



                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
