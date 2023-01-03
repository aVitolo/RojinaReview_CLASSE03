package rojinaReview.model.dao;

import rojinaReview.model.beans.Indirizzo;
import rojinaReview.model.utilities.ConPool;

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

    public ArrayList<Indirizzo> doRetriveByUser(int user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT via, numeroCivico, città, cap FROM Indirizzo WHERE id_videogiocatore=?");
        ps.setInt(1, user);
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

    public void doSave(int user, Indirizzo i) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO indirizzo VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, i.getVia());
        ps.setInt(2, i.getNumeroCivico());
        ps.setString(3, i.getCap());
        ps.setString(4, i.getCittà());
        ps.setInt(5, user);


        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");

    }

}