package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsDAO {

    /*
        Considerazioni
        -Aggiungere Corpo e Sottoticolo alla tabella Notizia
        -Aggiungere la tabella Commento al DB
     */

    public News doRetrieveByTitle(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.Nome,g.Cognome, n.Titolo, n.DataCaricamento FROM notizia n JOIN giornalista g on g.CF = n.Giornalista WHERE n.Titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                News n = new News();
                n.setGiornalista(rs.getString(1)+" "+rs.getString(2));
                n.setTitolo(rs.getString(3));/* verificare compatibilita tipi */
                n.setDataCaricamento(rs.getDate(4));

                /*
                Osservazione:
                - Riutilizzo del codice:
                    Ho usato GameDAO per popolare l'array però questo richiede l'esecuzione di n=giociConnessiAllaNotizia query
                 */

                ps = con.prepareStatement("SELECT Gioco From informare WHERE Notizia=?");
                ps.setString(1, titolo);
                rs = ps.executeQuery();
                GameDAO gDAO = new GameDAO();
                while (rs.next()) {
                    Game g = gDAO.doRetrieveByTitle(rs.getString(1));
                    n.getGiochi().add(g);
                }

                /*
                    Aggiungere singolarmnete i commenti
                    Eseguendo la seguente query ottengo tutti i commenti connessi alla recensione
                    Quindi ad ogni riga corrisponde un beans che vado ad aggiungere alla lista dei commenti
                */

                /*
                ps = con.prepareStatement("SELECT Testo, Data, User FROM commento WHERE Articolo=?");
                ps.setString(1, titolo);
                rs = ps.executeQuery();
                CommentoDAO cDAO = new CommentoDAO();
                while (rs.next()){
                    Commento c = new Commento();
                    c.setTesto(rs.getString(1));
                    c.setData(rs.getDate(2));
                    c.setUser(rs.getString(3));
                    n.getCommenti().add(c);
                }
                */

                /*
                    Aggiungere direttamente tutti i commenti
                */

                CommentoDAO cDAO = new CommentoDAO();
                n.setCommenti(cDAO.getCommentByArticol(titolo));

                return n;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<News> doRetrieveLast() {
        return null;
    }
}
