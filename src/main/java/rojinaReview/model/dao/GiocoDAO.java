package rojinaReview.model.dao;

import rojinaReview.model.beans.Gioco;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiocoDAO {
    private final Connection con;

    public GiocoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public GiocoDAO(Connection con) {
        this.con = con;
    }

    public Gioco doRetrieveByTitle(String titolo) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM gioco WHERE titolo=?");
        ps.setString(1, titolo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Gioco(
                    rs.getString(1),
                    rs.getString(3),
                    rs.getInt(5),
                    rs.getFloat(4),
                    rs.getDate(2),
                    rs.getString(6),
                    new PiattaformaDAO(con).doRetriveByGame(titolo),
                    new TipologiaDAO(con).doRetriveByGame(titolo));
        }

        return null;


    }

    public ArrayList<Gioco> doRetrieveAll() throws SQLException {
        ArrayList<Gioco> giochi = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM gioco");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Gioco g = new Gioco();

            g.setTitolo(rs.getString("titolo"));
            g.setDataDiRilascio(rs.getDate("dataDiRilascio"));
            g.setCasaDiSviluppo(rs.getString("casaDiSviluppo"));
            g.setMediaVoto(rs.getFloat("mediaVoto"));
            g.setNumeroVoti(rs.getInt("numeroVoti"));
            g.setCopertina(rs.getString("copertina"));
            g.setTipologie(new TipologiaDAO(con).doRetriveByGame(g.getTitolo()));
            g.setPiattaforme(new PiattaformaDAO(con).doRetriveByGame(g.getTitolo()));

            giochi.add(g);
        }
        return giochi;
    }

    public ArrayList<String> getGiocoByIdNotizia(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT g.titolo FROM gioco g join gioco_notizia gn on  g.titolo=gn.gioco WHERE notizia=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> g = new ArrayList<>();
        while (rs.next())
            g.add(rs.getString(1));

        return g;

    }

    public ArrayList<String> getGamesNames() throws SQLException {
        ArrayList<String> games = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT titolo FROM Gioco");
        ResultSet rs = ps.executeQuery();

        while (rs.next())
            games.add(rs.getString("titolo"));

        return games;
    }

    public void doSave(Gioco g) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO gioco VALUES" +
                "(?, ?, ?, ?, ?, ?)");

        ps.setString(1, g.getTitolo());
        ps.setDate(2, g.getDataDiRilascio());
        ps.setString(3, g.getCasaDiSviluppo());
        ps.setFloat(4, g.getMediaVoto());
        ps.setInt(5, g.getNumeroVoti());
        ps.setString(6, g.getCopertina());


        if (ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

}
