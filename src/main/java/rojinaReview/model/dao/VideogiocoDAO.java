package rojinaReview.model.dao;

import rojinaReview.model.beans.Videogioco;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideogiocoDAO {
    private final Connection con;

    public VideogiocoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public VideogiocoDAO(Connection con) {
        this.con = con;
    }

    public Videogioco doRetrieveById(int videogioco) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM videogioco WHERE id=?");
        ps.setInt(1, videogioco);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Videogioco g = new Videogioco();
            g.setId(rs.getInt("id"));
            g.setTitolo(rs.getString("titolo"));
            g.setDataDiRilascio(rs.getDate("dataDiRilascio"));
            g.setCasaDiSviluppo(rs.getString("casaDiSviluppo"));
            g.setMediaVoto(rs.getFloat("mediaVoto"));
            g.setNumeroVoti(rs.getInt("numeroVoti"));
            g.setCopertina(rs.getString("copertina"));
            g.setGeneri(new GenereDAO(con).doRetriveByGame(g.getId()));
            g.setPiattaforme(new PiattaformaDAO(con).doRetriveByGame(g.getId()));
            //g.setListaNotizie(new NotiziaDAO(con).doRetrieveByGameMentioned(g.getId()));
            return g;
        }

        return null;


    }

    //utilizzata per costruire la sezione videogiochi del giornalista
    public ArrayList<Videogioco> doRetrieveAll() throws SQLException {
        ArrayList<Videogioco> giochi = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM videogioco");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Videogioco g = new Videogioco();

            g.setId(rs.getInt("id"));
            g.setTitolo(rs.getString("titolo"));
            g.setDataDiRilascio(rs.getDate("dataDiRilascio"));
            g.setCasaDiSviluppo(rs.getString("casaDiSviluppo"));
            g.setMediaVoto(rs.getFloat("mediaVoto"));
            g.setNumeroVoti(rs.getInt("numeroVoti"));
            g.setCopertina(rs.getString("copertina"));
            g.setGeneri(new GenereDAO(con).doRetriveByGame(g.getId()));
            g.setPiattaforme(new PiattaformaDAO(con).doRetriveByGame(g.getId()));
            //g.setListaNotizie(new NotiziaDAO(con).doRetrieveByGameMentioned(g.getId()));

            giochi.add(g);
        }
        return giochi;
    }

    //utilizzata per vedere i giochi menzionati in una notizia
    public ArrayList<String> getGiocoByIdNotizia(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT v.titolo FROM videogioco v join videogioco_notizia vn on  v.id=vn.id_videogioco WHERE vn.id_notizia=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> g = new ArrayList<>();
        while (rs.next())
            g.add(rs.getString(1));
        return g;

    }

    public ArrayList<String> getGamesNames() throws SQLException {
        ArrayList<String> games = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT titolo FROM videogioco");
        ResultSet rs = ps.executeQuery();

        while (rs.next())
            games.add(rs.getString("titolo"));

        return games;
    }


    public void doSave(Videogioco g) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO videogioco VALUES " +
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

    public Videogioco doRetrieveByTitle(String gioco) {
        return null;
    }

    public int doRetrieveByTitle(String gioco) {
        return -1;
    }

    public int retrieveIdByName(String s) {
    }
}
