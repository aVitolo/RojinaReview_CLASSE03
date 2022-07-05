package model.dao;

import model.beans.Prodotto;
import model.utilities.ConPool;

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

    public Prodotto doRetrieveById(int id) throws SQLException {
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
            p.setImmagine(rs.getString(6));

            if(rs.getInt(7)==1){
                ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                ps.setInt(1, id);
                ResultSet r = ps.executeQuery();
                if (r.next()) {
                    p.getSconto().setNome(r.getString(1));
                    p.getSconto().setPercentuale(r.getFloat(2));
                }
            }

            p.setCategorie(new CategoriaDAO(con).doRetrieveByProductId(id));
            p.setMediaVoto(rs.getFloat(8));
            p.setNumeroVoti(rs.getInt(9));



            return p;
        }

        return null;

    }


    public ArrayList<Prodotto> doRetrieveLast() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM prodotto LIMIT 10");
        ResultSet rs = ps.executeQuery();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        while (rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setDescrizione(rs.getString(3));
            p.setDisponibilità(rs.getInt(4));
            p.setPrezzo(rs.getFloat(5));
            p.setImmagine(rs.getString(6));

            if (rs.getInt(7) == 1) {
                ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                ps.setInt(1, p.getId());
                ResultSet r = ps.executeQuery();
                if (r.next()) {
                    p.getSconto().setNome(r.getString(1));
                    p.getSconto().setPercentuale(r.getFloat(2));
                }
            }

            p.setCategorie(new CategoriaDAO(con).doRetrieveByProductId(p.getId()));
            p.setMediaVoto(rs.getFloat(8));
            p.setNumeroVoti(rs.getInt(9));

            prodotti.add(p);
        }
        return prodotti;
    }
}
