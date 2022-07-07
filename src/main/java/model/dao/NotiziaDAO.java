package model.dao;

import model.beans.Notizia;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotiziaDAO {
    private final Connection con;

    public NotiziaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public NotiziaDAO(Connection con) {
        this.con = con;
    }

    public Notizia doRetrieveById(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT g.Nome,g.Cognome, n.id, n.titolo, n.testo, n.dataCaricamento, n.immagine FROM notizia n JOIN giornalista g on g.id = n.giornalista WHERE n.id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Notizia n = new Notizia();
            n.setGiornalista(rs.getString(1) + " " + rs.getString(2));
            n.setId(rs.getInt(3));
            n.setTitolo(rs.getString(4));
            n.setTesto(rs.getString(5));
            n.setDataCaricamento(rs.getDate(6));
            n.setImmagine(rs.getString(7));
            //n.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(8)));
            n.setCommenti(new CommentoDAO(con).getCommentById(id, "Notizia"));
            n.setGiochi(new GiocoDAO(con).getGiocoByIdNotizia(id));
            return n;
        }

        return null;
    }

    public ArrayList<Notizia> doRetrieveLast() throws SQLException {

        ArrayList<Notizia> notizie = new ArrayList<>();

        PreparedStatement ps =
                con.prepareStatement("SELECT g.Nome,g.Cognome, n.id, n.titolo, n.testo, n.dataCaricamento, n.immagine " +
                        "FROM notizia n JOIN giornalista g on g.id = n.giornalista " +
                        "ORDER BY n.id DESC LIMIT 12");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();
            n.setGiornalista(rs.getString(1) + " " + rs.getString(2));
            n.setId(rs.getInt(3));
            n.setTitolo(rs.getString(4));
            n.setTesto(rs.getString(5));
            n.setDataCaricamento(rs.getDate(6));
            n.setImmagine(rs.getString(7));
            n.setCommenti(new CommentoDAO(con).getCommentById(n.getId(), "Notizia"));
            n.setGiochi(new GiocoDAO(con).getGiocoByIdNotizia(n.getId()));
            notizie.add(n);
        }

        return notizie;
    }

    public ArrayList<Notizia> doRetrieveByIdJournalist(int id) throws SQLException {
        ArrayList<Notizia> notizie = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT g.Nome,g.Cognome, n.id, n.titolo, n.testo, n.dataCaricamento, n.immagine " +
                "FROM notizia n JOIN giornalista g on g.id = n.giornalista WHERE n.giornalista=? ORDER BY n.dataCaricamento DESC");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();
            n.setGiornalista(rs.getString(1) + " " + rs.getString(2));
            n.setId(rs.getInt(3));
            n.setTitolo(rs.getString(4));
            n.setTesto(rs.getString(5));
            n.setDataCaricamento(rs.getDate(6));
            n.setImmagine(rs.getString(7));
            //n.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(8)));
            n.setCommenti(new CommentoDAO(con).getCommentById(id, "Notizia"));
            n.setGiochi(new GiocoDAO(con).getGiocoByIdNotizia(id));
            notizie.add(n);
        }
        return notizie;
    }

    public void doSave(Notizia n, int idGiornalista) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Notizia (testo, giornalista, titolo, dataCaricamento, immagine) VALUES" +
                "(?, ?, ?, ?, ?)");

        ps.setString(1, n.getTesto());
        ps.setInt(2, idGiornalista);
        ps.setString(3, n.getTitolo());
        ps.setDate(4, n.getDataCaricamento());
        ps.setString(5, n.getImmagine());

        if (ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

    //fa l'insert nella table gioco_notizia per i giochi menzionati
    public void doSaveMentioned(ArrayList<String> mentioned, int idGiornalista) throws SQLException {
        int idNotizia;
        PreparedStatement ps = con.prepareStatement("SELECT id FROM Notizia ORDER BY id DESC LIMIT 1"); //prende l'id della notizia appena inserita in insertNewServlet
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            idNotizia = rs.getInt("id");
        else return;

        for (String s : mentioned) {
            ps = con.prepareStatement("INSERT INTO gioco_notizia (giornalista, notizia, gioco) VALUES (?, ?, ?)");

            ps.setInt(1, idGiornalista);
            ps.setInt(2, idNotizia);
            ps.setString(3, s);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");
        }
    }

    public ArrayList<Notizia> updateContent(String offset, String piattaforma, String tipologia, String ordina) throws SQLException {
        ArrayList<Notizia> notizie = new ArrayList<>();
        int limit = 12;
        String select = " SELECT n.id, n.titolo, n.testo, n.immagine ";
        String from = " FROM notizia n";
        from += (!piattaforma.equals("Piattaforma") ?
                    " JOIN gioco_notizia gn1 on n.id=gn1.notizia JOIN gioco_piattaforma gp on gn1.gioco=gp.gioco "
                    : " ");
        from += (!tipologia.equals("Tipologia") ?
                    " JOIN gioco_notizia gn2 on n.id=gn2.notizia JOIN gioco_tipologia gt on gn2.gioco=gt.gioco "
                    : " ");
        String where = " WHERE ";
        where += (!piattaforma.equals("Piattaforma") ?
                 " gp.piattaforma='" + piattaforma + "'" :
                 "");
        where += (!tipologia.equals("Tipologia") ?
                    (where.equals(" WHERE ") ?
                            " gt.tipologia='" + tipologia + "'" :
                            " AND gt.tipologia='" + tipologia + "'")
                  : "");
        if (where.equals(" WHERE ")) where = " ";
        String order = " ORDER BY n.dataCaricamento " +
                (ordina.equals("Least Recent") ? " ASC " : " DESC ") +
                " LIMIT "+ limit +" OFFSET "+ offset;

        PreparedStatement ps =
                con.prepareStatement(select + from + where + order);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Notizia n = new Notizia();
            n.setId(rs.getInt(1));
            n.setTitolo(rs.getString(2));
            n.setTesto(rs.getString(3));
            n.setImmagine(rs.getString(4));
            notizie.add(n);
        }

        return notizie;
    }
}
