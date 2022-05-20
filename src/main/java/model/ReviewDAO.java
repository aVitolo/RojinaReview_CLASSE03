package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {

    public Review doRetrieveByTitle(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.Nome,g.Cognome, r.Titolo, r.Voto, r.DataCaricamento FROM recensione r JOIN giornalista g on g.CF = r.Giornalista WHERE r.Titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Review r = new Review();
                r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
                r.setTitolo(rs.getString(4));
                r.setVoto(rs.getFloat(5));
                r.setDataCaricamento(rs.getDate(6));

                /*
                Osservazione:
                - Riutilizzo del codice:
                    Ho usato GameDAO per ottenere tutte le info riguardanti il gioco recensito
                 */
                GameDAO gDAO = new GameDAO();
                Game g = gDAO.doRetrieveByTitle(rs.getString(3));
                r.setGioco(g);

                /*
                    Aggiungere singolarmnete i commenti
                    Eseguendo la seguente query ottengo tutti i commenti connessi alla recensione
                    Quindi ad ogni riga corrisponde un beans che vado ad aggiungere alla lista dei commenti
                 */

                /*
                ps = con.prepareStatement("SELECT Testo, Data, User FROM commento  WHERE Articolo=?");
                ps.setString(1, titolo);
                rs = ps.executeQuery();
                CommentoDAO cDAO = new CommentoDAO();
                while (rs.next()){
                    Commento c = new Commento();
                    c.setTesto(rs.getString(1));
                    c.setData(rs.getDate(2));
                    c.setUser(rs.getString(3));
                    r.getCommenti().add(c);
                }
                */

                /*
                    Aggiungere direttamente tutti i commenti
                */

                CommentoDAO cDAO = new CommentoDAO();
                r.setCommenti(cDAO.getCommentByArticol(titolo));

                return r;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Review> doRetrieveLast() {
        return null;
    }
}
