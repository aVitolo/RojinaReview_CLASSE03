package rojinaReview.model.dao;

import rojinaReview.model.beans.Articolo;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.GenericStaffDAO;

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

    public Giornalista doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Giornalista WHERE email=? ");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Giornalista(rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("immagine"),
                    rs.getBoolean("verificato"),
                    null);
        }

        return null;
    }

    public Giornalista doRetrieveById(int giornalista) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Giornalista WHERE id=?");
        ps.setInt(1, giornalista);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Giornalista(rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("immagine"),
                    rs.getBoolean("verificato"),
                    null);
        }

        return null;
    }

    /*
        Non serve?
        Permetteva al Manager di visualizzare tutti i giornalisti quindi ora e' superflua
     */
    public ArrayList<Giornalista> doRetriveAll() throws SQLException, UnsupportedEncodingException {
        ArrayList<Giornalista> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Giornalista");

        while(rs.next()){
            list.add(new Giornalista(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("immagine"),
                    rs.getBoolean("verificato"),
                    null));
        }
        return list;
    }

    //non serve?
    public boolean doRemoveById(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Giornalista WHERE id=?");
        ps.setInt(1,id);
        int i = ps.executeUpdate();
        return i == 1;
    }

    public ArrayList<Giornalista> doRetrieveNonVerifiedJournalists() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT email, nome, cognome FROM giornalista WHERE verificato = 0");
        ResultSet rs = ps.executeQuery();
        ArrayList<Giornalista> giornalisti = new ArrayList<>();
        while(rs.next())
        {
            Giornalista g = new Giornalista();

            g.setEmail(rs.getString("email"));
            g.setNome(rs.getString("nome"));
            g.setCognome(rs.getString("cognome"));

            giornalisti.add(g);
        }

        return giornalisti;
    }

}
