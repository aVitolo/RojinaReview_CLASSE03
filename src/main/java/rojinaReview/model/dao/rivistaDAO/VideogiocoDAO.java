package rojinaReview.model.dao.rivistaDAO;

import rojinaReview.model.beans.Videogioco;
import rojinaReview.utilities.ConPool;

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

    public ArrayList<String> doRetrieveAllNames() throws SQLException {
        ArrayList<String> giochi = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT titolo FROM videogioco");
        ResultSet rs = ps.executeQuery();

        while (rs.next())
            giochi.add(rs.getString("titolo"));

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


    public int doSave(Videogioco g) throws SQLException {
        int id = 0;
        PreparedStatement ps = con.prepareStatement("INSERT INTO videogioco (titolo, dataDiRilascio, casaDiSviluppo, mediaVoto, numeroVoti, copertina) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?)");

        ps.setString(1, g.getTitolo());
        ps.setDate(2, g.getDataDiRilascio());
        ps.setString(3, g.getCasaDiSviluppo());
        ps.setFloat(4, g.getMediaVoto());
        ps.setInt(5, g.getNumeroVoti());
        ps.setString(6, g.getCopertina());


        if (ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");

        ps = con.prepareStatement("SELECT id FROM videogioco ORDER BY id DESC LIMIT 1");
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            id = rs.getInt(1);

        return id;
    }

    public int retrieveIdByName(String s) throws SQLException {
        int videogioco = 0;
        PreparedStatement ps = con.prepareStatement("SELECT id FROM videogioco WHERE titolo=?");
        ps.setString(1, s);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            videogioco = rs.getInt("id");
        else
            throw new SQLException();

        return videogioco;

    }
}
