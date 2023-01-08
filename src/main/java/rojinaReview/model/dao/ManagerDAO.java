package rojinaReview.model.dao;

import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Utente;
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
        PreparedStatement ps = con.prepareStatement("SELECT * FROM manager WHERE email=? and verificato=1");
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

    public ArrayList<Utente> doRetriveInQueeue() throws SQLException, UnsupportedEncodingException {
        ArrayList<Utente> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT g.id,g.nome,g.cognome,g.email FROM Manager g WHERE g.verificato=0");

        while(rs.next()){
            Manager g = new Manager();
            g.setId( rs.getInt("id"));
            g.setCognome(rs.getString("cognome"));
            g.setNome(rs.getString("nome"));
            g.setEmail(  rs.getString("email"));
            list.add(g);
        }
        return list;
    }

    public void doVerificaManager(int id) throws SQLException, UnsupportedEncodingException {
        Statement stmt = con.createStatement();
        PreparedStatement ps =
                con.prepareStatement("UPDATE manager g SET g.verificato=1 WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    public void doRemoveFromQueeue(int id) throws SQLException, UnsupportedEncodingException {
        this.doRemoveById(id);
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

    public void doSendRequestManager(Manager manager) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO manager(email, password, nome, cognome, verificato) VALUES (?,?,?,?,?)");
        ps.setString(1, manager.getEmail());
        ps.setString(2, manager.getPassword());
        ps.setString(3, manager.getNome());
        ps.setString(4, manager.getCognome());
        ps.setInt(5, 0); //account non verificato
        ResultSet rs;
        try{
            ps.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == 23000) //integrity constraint violation
            {
                ps = con.prepareStatement("SELECT email FROM manager WHERE email=?");
                ps.setString(1, manager.getEmail());
                rs = ps.executeQuery();
                if(rs.next())
                    throw new SQLException("email");
            }
        }

    }

    //non serve?
    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Manager WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }

}
