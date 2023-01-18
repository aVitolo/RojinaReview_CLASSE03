package rojinaReview.model.dao.opinioneDAO;

import rojinaReview.model.beans.Commento;
import rojinaReview.model.dao.rivistaDAO.NotiziaDAO;
import rojinaReview.model.dao.rivistaDAO.RecensioneDAO;
import rojinaReview.model.dao.shopDAO.ProdottoDAO;
import rojinaReview.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class CommentoDAO {
    private Connection con;

    public CommentoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public CommentoDAO(Connection con) {
        this.con = con;
    }

    /*table: Prodotto-Recensione-Notizia*/
    public ArrayList<Commento> getCommentByContentId(int id, int tipo) throws SQLException {
        String stringType = null;
        if(tipo == 0)
            stringType = "id_prodotto";
        else if(tipo == 1)
            stringType = "id_recensione";
        else if(tipo == 2)
            stringType = "id_notizia";
        String query = "SELECT c.id, c.testo, c.dataScrittura, c.id_videogiocatore, v.nickname FROM commento c JOIN videogiocatore v on v.id = c.id_videogiocatore WHERE " + stringType + "=? " + "and v.bannato = 0 ORDER BY dataScrittura DESC";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Commento> commenti = new ArrayList();
        while (rs.next()) {
            Commento c = new Commento();

            c.setId(rs.getInt(1));
            c.setTesto(rs.getString(2));
            c.setDataScrittura(rs.getTimestamp(3));
            c.setIdVideogiocatore(rs.getInt(4));
            c.setNicknameVideogiocatore(rs.getString(5));


            commenti.add(c);
            }
        return commenti;

    }
    public Commento getCommentById(int id) throws SQLException {
        String query = "SELECT c.id, c.id_videogiocatore FROM commento c where id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Commento c = new Commento();
        if (rs.next()) {
            c.setId(rs.getInt(1));
            c.setIdVideogiocatore(rs.getInt(2));
            return c;

        }
        return null;
    }
    public ArrayList<Commento> getCommentsByUser(int user) throws SQLException {
        ArrayList<Commento> commenti = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT id, testo, dataScrittura, id_prodotto, id_recensione, id_notizia FROM commento WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Commento c = new Commento();

            c.setId(rs.getInt(1));
            c.setTesto(rs.getString(2));
            c.setDataScrittura(rs.getTimestamp(3));
            int idContenuto = 0;
            String contenuto = null;
            int tipo = -1;
            /*
                Da testare
             */
            if(rs.getObject("id_prodotto") != null)
            {
                idContenuto = rs.getInt("id_prodotto");
                contenuto = new ProdottoDAO(con).retrieveNome(rs.getInt("id_prodotto"));
                tipo = 0;
            }

            else if (rs.getObject("id_recensione") != null)
            {
                idContenuto = rs.getInt("id_recensione");
                contenuto = new RecensioneDAO(con).retrieveNome(rs.getInt("id_recensione"));
                tipo = 1;
            }

            else if (rs.getObject("id_notizia") != null)
            {
                idContenuto = rs.getInt("id_notizia");
                contenuto = new NotiziaDAO(con).retrieveNome(rs.getInt("id_notizia"));
                tipo = 2;
            }

            c.setIdContenuto(idContenuto);
            c.setNomeContenuto(contenuto);
            c.setTipo(tipo);

            commenti.add(c);
        }


        commenti.sort(Comparator.comparing(c -> c.getDataScrittura()));

        return commenti;

    }

    public void doSave(Commento c, int user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO commento (testo, dataScrittura, id_videogiocatore, id_prodotto, id_recensione, id_notizia) VALUES (?, ?, ?, ?, ?, ?)");
        ps.setString(1, c.getTesto());
        ps.setTimestamp(2, c.getDataScrittura());
        ps.setInt(3, user);
        /*
            se non setto gli altri parametri viene direttamente messo null o lanciata eccezione?
            proviamo ad utilizzare setNull.
        */
        //0 prodotto, 1 recensione, 2 notizia
        int tipo = c.getTipo();
        if(tipo == 0) {
            ps.setInt(4, c.getIdContenuto());
            ps.setNull(5, 0);
            ps.setNull(6, 0);
        }
        if(tipo == 1) {
            ps.setNull(4, 0);
            ps.setInt(5, c.getIdContenuto());
            ps.setNull(6, 0);
        }
        if(tipo == 2) {
            ps.setNull(4, 0);
            ps.setNull(5, 0);
            ps.setInt(6, c.getIdContenuto());
        }
        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

    public void deleteComment(int commento) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM commento WHERE id=?");
        ps.setInt(1, commento);
        ps.executeUpdate();
    }

    public void deleteAll(int content, int type) throws SQLException//0 prodotto, 1 recensione, 2 notizia
    {
        PreparedStatement ps;
        if(type == 0)
        {
            ps = con.prepareStatement("DELETE FROM commento WHERE id_prodotto=?");
            ps.setInt(1, content);
            ps.executeUpdate();
        }
        else if (type == 1)
        {
            ps = con.prepareStatement("DELETE FROM commento WHERE id_recensione=?");
            ps.setInt(1, content);
            ps.executeUpdate();
        }
        else if (type == 2)
        {
            ps = con.prepareStatement("DELETE FROM commento WHERE id_notizia=?");
            ps.setInt(1, content);
            ps.executeUpdate();
        }
    }

    public void deleteByText(String text) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM commento WHERE testo=? AND id>10");
        ps.setString(1, text);
        ps.executeUpdate();
    }

}
