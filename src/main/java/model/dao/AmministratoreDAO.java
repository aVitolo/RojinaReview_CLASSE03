package model.dao;

import model.beans.Amministratore;
import model.utilities.ConPool;
import model.utilities.GenericStaffDAO;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class AmministratoreDAO implements GenericStaffDAO {
    private final Connection con;


    public AmministratoreDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public AmministratoreDAO(Connection con) {
        this.con = con;
    }

    public Object doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Amministratore WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Amministratore(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getInt("id"));
        }

        return null;
    }

    public ArrayList<Amministratore> doRetriveAll() throws SQLException, UnsupportedEncodingException {
        ArrayList<Amministratore> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Amministratore");

        while(rs.next()){
            list.add(new Amministratore(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getInt("id")));
        }
        return list;
    }

    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Amministratore WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }

}
