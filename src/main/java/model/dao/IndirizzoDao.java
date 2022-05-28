package model.dao;

import model.beans.Indirizzo;

import java.sql.*;
import java.util.ArrayList;

public class IndirizzoDao {
    Connection con;

    //Così da creare una sola connessione per ogni dao
    public IndirizzoDao() throws SQLException {
        this.con = ConPool.getConnection();
    }

    //In alternativa si potrebbe passare la connessione al costruttore del dao per diminuirle ancora
    public IndirizzoDao(Connection con) throws SQLException {
        this.con = con;
    }

    public Indirizzo doRetriveByEmailUtente(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT via, numeroCivico, città, cap FROM Indirizzo WHERE utente=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        Indirizzo indirizzoTmp = new Indirizzo();
        if (rs.next()) {
            indirizzoTmp = new Indirizzo(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4));
        }
        return indirizzoTmp;
    }


    public ArrayList<Indirizzo> doRetriveListByEmailUtente(String email) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT via, numeroCivico, città, cap FROM Indirizzo WHERE utente=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        ArrayList<Indirizzo> indirizzi = new ArrayList<>();
        Indirizzo indirizzoTmp = null;
        //da controllare, potrebbe dare null pointer exception se l' email non e' presente nel db
        while (rs.next()) {
            indirizzoTmp = new Indirizzo(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4));

            indirizzi.add(indirizzoTmp);
        }

        return indirizzi;
    }
}