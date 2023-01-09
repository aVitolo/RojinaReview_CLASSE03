package rojinaReview.model.dao;

import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Utente;
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

    public Giornalista doRetriveByEmail(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Giornalista WHERE email=? ");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Giornalista g = new Giornalista();

            g.setId(rs.getInt("id"));
            g.setEmail(rs.getString("email"));
            g.setPassword(rs.getString("password"));
            g.setNome(rs.getString("nome"));
            g.setCognome(rs.getString("cognome"));
            g.setImmagine(rs.getString("immagine"));
            g.setVerificato(rs.getBoolean("verificato"));

            return g;
        }
        else
            throw new SQLException("Invalid email");

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

    public ArrayList<Utente> doRetriveInQueeue() throws SQLException, UnsupportedEncodingException {
        ArrayList<Utente> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT g.id,g.nome,g.cognome,g.email FROM Giornalista g WHERE g.verificato=0");

        while(rs.next()){
            Giornalista g = new Giornalista();
            g.setId( rs.getInt("id"));
            g.setCognome(rs.getString("cognome"));
            g.setNome(rs.getString("nome"));
            g.setEmail(  rs.getString("email"));
            list.add(g);
        }
        return list;
    }

    public void doVerificaGiornalista(int id) throws SQLException, UnsupportedEncodingException {
        Statement stmt = con.createStatement();
        PreparedStatement ps =
                con.prepareStatement("UPDATE giornalista g SET g.verificato=1 WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    public void doRemoveFromQueeue(int id) throws SQLException, UnsupportedEncodingException {
        this.doRemoveById(id);
    }

    //non serve?
    public boolean doRemoveById(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM giornalista g WHERE g.id=?");
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

    public void doSendRequestJournalist(Giornalista giornalista) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO giornalista(email, password, nome, cognome, verificato) VALUES (?,?,?,?,?)");
        ps.setString(1, giornalista.getEmail());
        ps.setString(2, giornalista.getPassword());
        ps.setString(3, giornalista.getNome());
        ps.setString(4, giornalista.getCognome());
        ps.setInt(5, 0); //account non verificato
        ResultSet rs;
        try{
            ps.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == 23000) //integrity constraint violation
            {
                ps = con.prepareStatement("SELECT email FROM giornalista WHERE email=?");
                ps.setString(1, giornalista.getEmail());
                rs = ps.executeQuery();
                if(rs.next())
                    throw new SQLException("email");
            }
        }

    }

}
