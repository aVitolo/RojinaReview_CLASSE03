package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiocoDAO {

    public Gioco doRetrieveByTitle(String titolo) {

        /*
        Considerazione
            Ha senso creare, per rendere più leggibile il codice, tre funzioni distinti
            -setInfo
            -setTipologie
            -setCategorie
         */

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM gioco WHERE titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Gioco g = new Gioco();
                g.setTitolo(rs.getString(1));
                g.setDataDiRilascio(rs.getDate(2));
                g.setCasaDiSviluppo(rs.getString(3));
                g.setMediaVoto(rs.getFloat(4));
                g.setNumeroVoti(rs.getInt(5));
                g.setCopertina(rs.getBytes(6));

                /*
                    Essendo l'array di Piattaforme e Tipologia di tipo stringa mi basta eseguire le due query per ottenere i dati necessari
                 */

                ps = con.prepareStatement("SELECT piattaforma FROM gioco_piattaforma WHERE gioco=?");
                ps.setString(1, titolo);
                rs = ps.executeQuery();
                while (rs.next())
                    g.getPiattaforme().add(rs.getString(1));

                ps = con.prepareStatement("SELECT tipologia FROM gioco_tipologia WHERE gioco=?");
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

    public ArrayList<Gioco> getGiocoByIdNotizia(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT gioco FROM gioco_notizia WHERE notizia=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Gioco> g = new ArrayList<>();
            while (rs.next()) {

            }
            return g;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

/* aggiornamento voto: la servlet invia il nuovo voto al DAO, questo controlla
se il voto è già presente o meno e quindi fare insert o update
 */