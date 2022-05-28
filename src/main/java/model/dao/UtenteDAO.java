package model.dao;

import model.beans.Indirizzo;
import model.beans.Pagamento;
import model.beans.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {
    Connection con;

    public UtenteDAO() throws SQLException {
        this.con = ConPool.getConnection();
    }

    public Utente doRetriveByEmail(String email) throws SQLException {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setNickname(rs.getString(2));
                u.setEmail(rs.getString(1));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setEta(rs.getInt(6));
                u.setOrdini(new OrdineDAO().doRetrieveByUser(email));
                u.setCarrello(new CarrelloDAO().doRetrieveByUser(email));

                IndirizzoDao iDao = new IndirizzoDao();
                ArrayList<Indirizzo> listaIndirizzi = iDao.doRetriveListByEmailUtente(email);
                u.setIndirizzi(listaIndirizzi);

                u.setTelefoni(UtenteDAO.doRetriveTelefonoByEmail(email,con));

                //Da rivedere, pagamentoDao esiste gia'?
                ps = con.prepareStatement("SELECT nome, cognome, numeroCarta, dataScadenza FROM Pagamento WHERE utente=?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                ArrayList<Pagamento> pagamenti = new ArrayList<>();
                while (rs.next()) {
                    Pagamento p = new Pagamento();
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
    }

    public static ArrayList<String> doRetriveTelefonoByEmail(String email, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT numero FROM Telefono WHERE utente=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> telefoni = new ArrayList<>();
        while (rs.next())
            telefoni.add(rs.getString(1));

        return telefoni;
    }
}
