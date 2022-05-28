package model.dao;

import model.beans.Recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneDAO {

    public Recensione doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.nome, g.cognome, r.id, r.titolo, r.testo, r.voto, r.DataCaricamento, r.gioco, r.immagine FROM recensione r JOIN giornalista g on g.id = r.giornalista WHERE r.id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Recensione r = new Recensione();
                r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
                r.setId(rs.getInt(3));
                r.setTitolo(rs.getString(4));
                r.setTesto(rs.getString(5));
                r.setVoto(rs.getFloat(6));
                r.setDataCaricamento(rs.getDate(7));
                r.setGioco(new GiocoDAO().doRetrieveByTitle(rs.getString(8)));
                r.setImmagine(rs.getBytes(9));
                r.setCommenti(new CommentoDAO().getCommentByIdRecensione(r.getId()));
                return r;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> doRetrieveLast() {
        return null;
    }
}
