package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {

    public Game doRetrieveByTitle(String titolo) {

        /*
        Considerazione
            Ha senso creare, per rendere più leggibile il codice, tre funzioni distinti
            -setInfo
            -setTipologie
            -setCategorie
         */

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Titolo, DataDiRilascio, MediaVoto, NumeroVoti FROM gioco WHERE Titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Game g = new Game();
                g.setTitolo(rs.getString(1));
                g.setDataDiRilascio(rs.getDate(2));
                g.setCasaDiSviluppo(rs.getString(3));
                g.setMediaVoto(rs.getFloat(4));
                g.setNumeroVoti(rs.getInt(5));

                /*
                    Essendo l'array di Piattaforme e Tipologia di tipo stringa mi basta eseguire le due query per ottenere i dati necessari
                 */

                ps = con.prepareStatement("SELECT Piattaforma FROM giocare WHERE Gioco=?");
                ps.setString(1, titolo);
                rs = ps.executeQuery();
                while (rs.next())
                    g.getPiattaforme().add(rs.getString(1));

                ps = con.prepareStatement("SELECT Tipologia FROM appartenere WHERE Gioco=?");
                ps.setString(1, titolo);
                rs = ps.executeQuery();
                while (rs.next())
                    g.getCategorie().add(rs.getString(1));

                return g;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
