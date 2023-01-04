package rojinaReview.model.dao;

import rojinaReview.model.beans.Commento;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class CommentoDAO {
    private Connection con;

    public CommentoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public CommentoDAO(Connection con) {
        this.con = con;
    }

    /*table: Prodotto-Recensione-Notizia*/
    public ArrayList<Commento> getCommentById(int id, int tipo) throws SQLException {
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
            /*
                Da testare
             */
            if(rs.getObject("id_prodotto") != null)
            {
                idContenuto = rs.getInt("id_prodotto");
                contenuto = new ProdottoDAO(con).retrieveNome(rs.getInt("id_prodotto"));
            }

            else if (rs.getObject("id_recensione") != null)
            {
                idContenuto = rs.getInt("id_recensione");
                contenuto = new RecensioneDAO(con).retrieveNome(rs.getInt("id_recensione"));
            }

            else if (rs.getObject("id_notizia") != null)
            {
                idContenuto = rs.getInt("id_notizia");
                contenuto = new NotiziaDAO(con).retrieveNome(rs.getInt("id_notizia"));
            }

            c.setIdContenuto(idContenuto);
            c.setNomeContenuto(contenuto);
            commenti.add(c);
        }


        commenti.sort(Comparator.comparing(c -> c.getDataScrittura()));

        return commenti;

    }

    public void doSave(Commento c, int tipo) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO commento VALUES (?, ?, ?, ?, ?, ?)");
        ps.setString(1, c.getTesto());
        ps.setTimestamp(2, c.getDataScrittura());
        ps.setInt(3, c.getIdVideogiocatore());
        /*
            se non setto gli altri parametri viene direttamente messo null o lanciata eccezione?
            proviamo ad utilizzare setNull.
        */
        if(tipo == 0) {
            ps.setInt(4, c.getIdContenuto());
            ps.setNull(5, 6);
        }
        if(tipo == 1) {
            ps.setInt(5, c.getIdContenuto());
            ps.setNull(4, 6);
        }
        if(tipo == 2) {
            ps.setInt(6, c.getIdContenuto());
            ps.setNull(5, 4);
        }
        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

    public void deleteComment(int commento) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM commento WHERE id=?");
        ps.setInt(1, commento);
        ps.executeUpdate();
    }

    /*
        Neccessario implementare?
     */
    public ArrayList<Commento> getCommentByUser(int id) {
        return null;
    }
}
