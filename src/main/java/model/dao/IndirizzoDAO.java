package model.dao;

import model.beans.Indirizzo;
import model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class IndirizzoDAO {
    private final Connection con;

    //Così da creare una sola connessione per ogni dao
    public IndirizzoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    //In alternativa si potrebbe passare la connessione al costruttore del dao per diminuirle ancora
    public IndirizzoDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Indirizzo> doRetriveByUser(String user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT via, numeroCivico, città, cap FROM Indirizzo WHERE utente=?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Indirizzo> indirizzi = new ArrayList<>();
        //da controllare, potrebbe dare null pointer exception se l' email non e' presente nel db
        while (rs.next()) {
            indirizzi.add(new Indirizzo(
                    rs.getString(1),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(2)));
        }
        return indirizzi;
    }

    public void doSave(String user, Indirizzo i) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO indirizzo VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, user);
        ps.setString(2, i.getVia());
        ps.setInt(3, i.getNumeroCivico());
        ps.setString(4, i.getCittà());
        ps.setString(5, i.getCap());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");

    }

}