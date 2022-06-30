package model.dao;

import model.beans.Indirizzo;
import model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class IndirizzoDAO {
    private Connection con;

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

}