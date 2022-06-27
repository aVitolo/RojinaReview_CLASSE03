package model.dao;

import model.beans.Notizia;
import model.utilities.GiocoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotiziaDAO {
    private Connection con;

    public NotiziaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public NotiziaDAO(Connection con) {
        this.con = con;
    }

    public Notizia doRetrieveById(int id ) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT g.Nome,g.Cognome, n.id, n.titolo, n.testo, n.dataCaricamento, n.immagine FROM notizia n JOIN giornalista g on g.id = n.giornalista WHERE n.id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Notizia n = new Notizia();
            n.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            n.setId(rs.getInt(3));
            n.setTitolo(rs.getString(4));
            n.setTesto(rs.getString(5));
            n.setDataCaricamento(rs.getDate(6));
            n.setImmagine(rs.getBytes(7));
            n.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(8)));
            n.setCommenti(new CommentoDAO(con).getCommentById(id,"commentonotizia"));
            n.setGiochi(new GiocoDAO(con).getGiocoByIdNotizia(id));
            return n;
        }

        return null;
    }

    public ArrayList<Notizia> doRetrieveLast()  throws SQLException {

        ArrayList<Notizia> notizie = new ArrayList<>();

        PreparedStatement ps =
                con.prepareStatement("SELECT g.Nome,g.Cognome, n.id, n.titolo, n.testo, n.dataCaricamento, n.immagine " +
                                         "FROM notizia n JOIN giornalista g on g.id = n.giornalista " +
                                         "ORDER BY n.DataCaricamento DESC LIMIT 10");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();
            n.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            n.setId(rs.getInt(3));
            n.setTitolo(rs.getString(4));
            n.setTesto(rs.getString(5));
            n.setDataCaricamento(rs.getDate(6));
            n.setImmagine(rs.getBytes(7));
            //n.setCommenti(new CommentoDAO(con).getCommentById(n.getId(),"notizia"));
            //n.setGiochi(new GiocoDAO(con).getGiocoByIdNotizia(n.getId()));
            notizie.add(n);
        }

        return notizie;
    }

}
