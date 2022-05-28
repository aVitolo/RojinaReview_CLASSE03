package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {
    public Utente doRetriveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Utente u = new Utente();
                u.setNickname(rs.getString(2));
                u.setEmail(rs.getString(1));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setEta(rs.getInt(6));
                u.setOrdini(new OrdineDAO().doRetrieveByUser(email));
                u.setCarrello(new CarrelloDAO().doRetrieveByUser(email));

                ps = con.prepareStatement("SELECT via, numeroCivico, città, cap FROM indirizzo WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<String> indirizzi = new ArrayList<>();
                while(rs.next())
                    indirizzi.add(  rs.getString(1)+" "+
                                    rs.getString(2)+" "+
                                    rs.getString(3)+" "+
                                    rs.getString(4));
                u.setIndirizzi(indirizzi);

                ps = con.prepareStatement("SELECT numero FROM telefono WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<String> telefoni = new ArrayList<>();
                while(rs.next())
                    telefoni.add(rs.getString(1));
                u.setTelefoni(telefoni);

                ps = con.prepareStatement("SELECT nome, cognome, numeroCarta, dataScadenza FROM pagamento WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<Pagemento> pagamenti = new ArrayList<>();
                while(rs.next()) {
                    Pagemento p = new Pagemento();
                    p.setNome(rs.getString(1));
                    p.setCognome(rs.getString(2));
                    p.setNumeroCarta(rs.getString(3));
                    p.setDataScadenza(rs.getDate(4));
                    pagamenti.add(p);
                }
                u.setPagamenti(pagamenti);

                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
