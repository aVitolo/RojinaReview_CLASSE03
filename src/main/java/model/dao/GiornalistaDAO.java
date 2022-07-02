package model.dao;

import model.beans.Giornalista;
import model.utilities.ConPool;
import model.utilities.GenericStaffDAO;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GiornalistaDAO implements GenericStaffDAO {
    private Connection con;


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


}
