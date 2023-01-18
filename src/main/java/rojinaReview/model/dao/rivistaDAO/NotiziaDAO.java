package rojinaReview.model.dao.rivistaDAO;

import rojinaReview.model.beans.Notizia;
import rojinaReview.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotiziaDAO {
    private final Connection con;

    public Connection getCon() {
        return con;
    }

    public NotiziaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public NotiziaDAO(Connection con) {
        this.con = con;
    }

    public Notizia doRetrieveById(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM notizia WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Notizia n = new Notizia();

            n.setId(rs.getInt(1));
            n.setNome(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            n.setDataScrittura(rs.getDate(5));
            n.setId_Giornalista(rs.getInt(6));

            return n;
        }

        return null;
    }

    public ArrayList<Notizia> doRetrieveByGameMentioned(int game) throws SQLException {
        ArrayList<Notizia> notizie = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM notizia n JOIN videogioco_notizia vn ON n.id = vn.id_notizia WHERE vn.id_videogioco=?");
        ps.setInt(1, game);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Notizia n = new Notizia();

            n.setId(rs.getInt(1));
            n.setNome(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            n.setDataScrittura(rs.getDate(5));

            notizie.add(n);
        }

        return notizie;
    }

    public ArrayList<Notizia> doRetrieveLast() throws SQLException {

        ArrayList<Notizia> notizie = new ArrayList<>();

        PreparedStatement ps =
                con.prepareStatement("" +
                        "SELECT * FROM notizia ORDER BY id DESC LIMIT 13");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();

            n.setId(rs.getInt(1));
            n.setNome(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            n.setDataScrittura(rs.getDate(5));

            notizie.add(n);
        }

        return notizie;
    }

    public ArrayList<Notizia> doRetrieveByIdJournalist(int giornalista) throws SQLException {
        ArrayList<Notizia> notizie = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT n.id, n.nome, n.testo, n.immagine, n.dataScrittura " +
                "FROM notizia n JOIN giornalista g on g.id = n.id_giornalista WHERE n.id_giornalista=? ORDER BY n.dataScrittura DESC");
        ps.setInt(1, giornalista);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();

            n.setId(rs.getInt(1));
            n.setNome(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            n.setDataScrittura(rs.getDate(5));

            notizie.add(n);
        }

        return notizie;
    }

    public void doSave(Notizia n, int idGiornalista) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Notizia (testo, id_giornalista, nome, dataScrittura, immagine) VALUES" +
                "(?, ?, ?, ?, ?)");

        ps.setString(1, n.getTesto());
        ps.setInt(2, idGiornalista);
        ps.setString(3, n.getNome());
        ps.setDate(4, n.getDataScrittura());
        ps.setString(5, n.getImmagine());

        if (ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

    //fa l'insert nella table gioco_notizia per i giochi menzionati
    public void doSaveMentioned(HashMap<Integer, String> mentioned, int idNotizia) throws SQLException {
        PreparedStatement ps;


        for (Map.Entry<Integer, String> set : mentioned.entrySet()) {
            ps = con.prepareStatement("INSERT INTO videogioco_notizia (id_notizia, id_videogioco) VALUES (?, ?)");
            ps.setInt(1, idNotizia);
            ps.setInt(2, set.getKey());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");
        }
    }

    public ArrayList<Notizia> updateContent(String piattaforma, String genere, String ordina) throws SQLException {
        ArrayList<Notizia> notizie = new ArrayList<>();
        int limit = 12;
        String select = " SELECT n.id, n.nome, n.testo, n.immagine ";
        String from = " FROM notizia n";
        from += (!piattaforma.equals("Piattaforma") ?
                " JOIN Videogioco_Notizia gn1 on n.id=gn1.id_notizia JOIN Videogioco_Piattaforma gp on gn1.id_videogioco=gp.id_videogioco "
                : " ");
        from += (!genere.equals("Genere") ?
                " JOIN Videogioco_Notizia gn2 on n.id=gn2.id_notizia JOIN Videogioco_Genere gt on gn2.id_videogioco=gt.id_videogioco "
                : " ");
        String where = " WHERE ";
        where += (!piattaforma.equals("Piattaforma") ?
                " gp.piattaforma='" + piattaforma + "'" :
                "");
        where += (!genere.equals("Genere") ?
                (where.equals(" WHERE ") ?
                        " gt.genere='" + genere + "'" :
                        " AND gt.genere='" + genere + "'")
                : "");
        if (where.equals(" WHERE ")) where = " ";
        String order = " ORDER BY n.dataScrittura " +
                (ordina.equals("Least Recent") ? " ASC " : " DESC ");
        System.out.println(select + from + where + order);
        PreparedStatement ps =
                con.prepareStatement(select + from + where + order);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();
            n.setId(rs.getInt(1));
            n.setNome(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            notizie.add(n);
        }

        return notizie;
    }

    public ArrayList<Notizia> doRetriveAll() throws SQLException {
        ArrayList<Notizia> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Notizia");

        while (rs.next()) {
            Notizia n = new Notizia();

            n.setId(rs.getInt(1));
            n.setNome(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            n.setDataScrittura(rs.getDate(5));

            list.add(n);
        }
        return list;
    }

    //non serve?
    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Notizia WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }

    public int doRetrieveLastId() throws SQLException {
        int article = 0;
        PreparedStatement ps = con.prepareStatement("SELECT id FROM notizia ORDER BY id DESC LIMIT 1");
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            article = rs.getInt(1);

        return article;
    }

    public Object doRetrieveByGame(int idVideogioco) {
        return null;
    }

    public String retrieveNome(int idNotizia) {
        return null;
    }

    public HashMap<Integer, String> doRetrieveMentionedGames(int idNotizia) throws SQLException {
        HashMap<Integer, String> mentionedGames = new HashMap<>();
        int idVideogioco = 0;
        String nomeVideogioco = null;
        PreparedStatement ps = con.prepareStatement("SELECT id_videogioco FROM videogioco_notizia WHERE id_notizia=?");
        ps.setInt(1, idNotizia);
        ResultSet rs = ps.executeQuery();
        ResultSet rsInt;
        while (rs.next())
        {
            idVideogioco = rs.getInt("id_videogioco");
            ps = con.prepareStatement("SELECT titolo FROM videogioco WHERE id=?");
            ps.setInt(1, idVideogioco);
            rsInt = ps.executeQuery();
            if(rsInt.next())
                nomeVideogioco = rsInt.getString("titolo");

            mentionedGames.put(idVideogioco, nomeVideogioco);
        }

        return mentionedGames;
    }

    public void doUpdate(Notizia notizia) throws SQLException {
        PreparedStatement ps;
        if(notizia.getImmagine() != null)
        {
            ps = con.prepareStatement("UPDATE notizia SET nome=?, testo=?, immagine=?, dataScrittura=? WHERE id=?");
            ps.setString(1, notizia.getNome());
            ps.setString(2, notizia.getTesto());
            ps.setString(3, notizia.getImmagine());
            ps.setDate(4, notizia.getDataScrittura());
            ps.setInt(5, notizia.getId());
        }
        else
        {
            ps = con.prepareStatement("UPDATE notizia SET nome=?, testo=?, dataScrittura=? WHERE id=?");
            ps.setString(1, notizia.getNome());
            ps.setString(2, notizia.getTesto());
            ps.setDate(3, notizia.getDataScrittura());
            ps.setInt(4, notizia.getId());
        }


        ps.executeUpdate();
    }


    public void deleteMentioned(int idNotizia) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("DELETE FROM videogioco_notizia WHERE id_notizia=?");
        ps.setInt(1, idNotizia);
        ps.executeUpdate();
    }



}