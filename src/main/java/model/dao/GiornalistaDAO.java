package model.dao;

import model.beans.Amministratore;
import model.beans.Giornalista;
import model.utilities.ConPool;
import model.utilities.GenericStaffDAO;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class GiornalistaDAO implements GenericStaffDAO {
    private final Connection con;

    public Connection getCon() {
        return con;
    }

    public GiornalistaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public GiornalistaDAO(Connection con) {
        this.con = con;
    }

    public Object doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Giornalista WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Giornalista(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getString("immagine"),
                    rs.getInt("id"));
        }

        return null;
    }
    public ArrayList<Giornalista> doRetriveAll() throws SQLException, UnsupportedEncodingException {
        ArrayList<Giornalista> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Giornalista");

        while(rs.next()){
            list.add(new Giornalista(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getString("immagine"),
                    rs.getInt("id")));
        }
        return list;
    }

    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Giornalista WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }

}
