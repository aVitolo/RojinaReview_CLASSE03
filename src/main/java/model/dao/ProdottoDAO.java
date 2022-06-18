package model.dao;

import model.beans.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {
    private Connection con;

    public ProdottoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public ProdottoDAO(Connection con)
    {
        this.con = con;
    }

    // Valutare se gestire lo sconto con left join

    public Prodotto doRetriveById(int id) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM prodotto WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Prodotto p = new Prodotto();
        if (rs.next()) {
            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setDescrizione(rs.getString(3));
            p.setDisponibilità(rs.getInt(4));
            p.setPrezzo(rs.getFloat(5));
            p.setImmagine(rs.getBytes(6));
            p.setCategorie(new CategoriaDAO(con).doRetrieveByProductId(id));

            if(rs.getInt(7)==1){
                ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                p.getSconto().setNome(rs.getString(1));
                p.getSconto().setPercentuale(rs.getFloat(2));
            }

            return p;
        }

        return null;

    }



}
