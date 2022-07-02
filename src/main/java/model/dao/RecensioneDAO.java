package model.dao;

import model.beans.Recensione;
import model.utilities.ConPool;
import model.utilities.GiocoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

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
            r.setImmagine(rs.getString(10));
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
            r.setImmagine(rs.getString(10));
            //r.setCommenti(new CommentoDAO(con).getCommentById(r.getId(),"commentorecensione"));
            recensioni.add(r);
        }

        return recensioni;

    }

    public void doSave(Recensione r, int idGiornalista) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO recensione " +
                "(testo, giornalista, gioco, titolo, voto, dataCaricamento, immagine) VALUES(?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, r.getTesto());
        ps.setInt(2, idGiornalista);
        ps.setString(3, r.getGioco().getTitolo());
        ps.setString(4, r.getTitolo());
        ps.setFloat(5, r.getVoto());
        ps.setDate(6, r.getDataCaricamento());
        ps.setString(7, r.getImmagine());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");


    }


}
