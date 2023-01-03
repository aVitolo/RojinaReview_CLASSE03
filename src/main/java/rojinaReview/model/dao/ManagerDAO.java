package rojinaReview.model.dao;

import rojinaReview.model.beans.Manager;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.GenericStaffDAO;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class ManagerDAO implements GenericStaffDAO {
    private final Connection con;

    public Connection getCon() {
        return con;
    }

    public ManagerDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public ManagerDAO(Connection con) {
        this.con = con;
    }

    public Manager doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM manager WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Manager m = new Manager();

            m.setId(rs.getInt(1));
            m.setEmail(rs.getString(2));
            m.setPassword(rs.getString(3));
            m.setNome(rs.getString(4));
            m.setCognome(rs.getString(5));
            m.setImmagine(rs.getString(6));
            m.setVerificato(rs.getBoolean(7));

            return m;
        }

        return null;
    }

    public Manager doRetrieveById(int manager) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM manager WHERE id=?");
        ps.setInt(1, manager);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Manager m = new Manager();

            m.setId(rs.getInt(1));
            m.setEmail(rs.getString(2));
            m.setPassword(rs.getString(3));
            m.setNome(rs.getString(4));
            m.setCognome(rs.getString(5));
            m.setImmagine(rs.getString(6));
            m.setVerificato(rs.getBoolean(7));

            return m;
        }

        return null;
    }

    public ArrayList<Manager> doRetriveAll() throws SQLException, UnsupportedEncodingException {
        ArrayList<Manager> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM manager");

        while(rs.next()){
            Manager m = new Manager();

            m.setId(rs.getInt(1));
            m.setEmail(rs.getString(2));
            m.setPassword(rs.getString(3));
            m.setNome(rs.getString(4));
            m.setCognome(rs.getString(5));
            m.setImmagine(rs.getString(6));
            m.setVerificato(rs.getBoolean(7));

            list.add(m);
        }
        return list;
    }

    //non serve?
    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Amministratore WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }

}
