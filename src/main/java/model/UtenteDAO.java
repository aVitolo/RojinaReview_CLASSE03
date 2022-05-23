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
                    con.prepareStatement("SELECT * FROM Utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Utente u = new Utente();
                u.setNickname(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setEtà(rs.getInt(5));

                ps = con.prepareStatement("SELECT * FROM indirizzo WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<String> indirizzi = new ArrayList<>();
                while(rs.next())
                    indirizzi.add(  rs.getString(1)+" "+    //indirizzo
                                    rs.getString(2)+" "+    //numero
                                    rs.getString(3)+" "+    //cap
                                    rs.getString(4)+" "+    //citta
                                    rs.getString(5));  //provincia
                u.setAddress(indirizzi);

                ps = con.prepareStatement("SELECT Numero FROM telefono WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<String> telefoni = new ArrayList<>();
                while(rs.next())
                    telefoni.add(rs.getString(1));
                u.setCellNumbers(telefoni);

                ps = con.prepareStatement("SELECT * FROM pagamento WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<MetodoPagemento> pagamenti = new ArrayList<>();
                while(rs.next()) {
                    MetodoPagemento p = new MetodoPagemento();
                    p.setNumeroCarta(rs.getString(1));
                    p.setDataScadenza(rs.getDate(2));
                    pagamenti.add(p);
                }
                u.setMetodiPagamento(pagamenti);

                u.setOrdini(new OrdineDAO().doRetrieveByUser(email));

                Carrello c = new Carrello();
                c.setCarrello(new CarrelloDAO().getUserCart(email));
                u.setCarello(c);

                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
