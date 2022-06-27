package model.dao;

import model.beans.Recensione;
import model.utilities.GiocoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneDAO {
    private Connection con;

    public RecensioneDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public RecensioneDAO(Connection con) {
        this.con = con;
    }

    public Recensione doRetrieveById(int id) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT g.nome, g.cognome, g.nome, r.id, r.titolo, r.testo, r.voto, r.DataCaricamento, r.gioco, r.immagine FROM recensione r JOIN giornalista g on g.id = r.giornalista WHERE r.id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Recensione r = new Recensione();
            r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            //r.setImgGiornalista(rs.getBytes(3));
            r.setId(rs.getInt(4));
            r.setTitolo(rs.getString(5));
            r.setTesto(rs.getString(6));
            r.setVoto(rs.getFloat(7));
            r.setDataCaricamento(rs.getDate(8));
            r.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(9)));
            r.setImmagine(rs.getBytes(10));
            r.setCommenti(new CommentoDAO(con).getCommentById(r.getId(),"recensione"));
            return r;
        }

        return null;

    }

    public ArrayList<Recensione> doRetrieveLast() throws SQLException{

        ArrayList<Recensione> recensioni = new ArrayList<>();

        PreparedStatement ps =
                con.prepareStatement("SELECT g.nome, g.cognome, g.nome ,r.id, r.titolo, r.testo, r.voto, r.DataCaricamento, r.gioco, r.immagine " +
                                         "FROM recensione r JOIN giornalista g on g.id = r.giornalista " +
                                         "ORDER BY r.DataCaricamento DESC LIMIT 10");


        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Recensione r = new Recensione();
            r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            //r.setImgGiornalista(rs.getBytes(3));
            r.setId(rs.getInt(4));
            r.setTitolo(rs.getString(5));
            r.setTesto(rs.getString(6));
            r.setVoto(rs.getFloat(7));
            r.setDataCaricamento(rs.getDate(8));
            //r.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(9)));
            r.setImmagine(rs.getBytes(10));
            //r.setCommenti(new CommentoDAO(con).getCommentById(r.getId(),"commentorecensione"));
            recensioni.add(r);
        }

        return recensioni;

    }

}
