package model.dao;

import model.beans.Piattaforma;
import model.beans.Tipologia;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PiattaformaDAO {
    private final Connection con;

    public PiattaformaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public PiattaformaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Piattaforma> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome FROM piattaforma");
        ResultSet rs = ps.executeQuery();
        ArrayList<Piattaforma> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(new Piattaforma(rs.getString(1)));
        return piattaforme;
    }

    public ArrayList<Piattaforma> doRetriveByGame(String titolo) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT piattaforma FROM gioco_piattaforma WHERE gioco=?");
        ps.setString(1, titolo);
        ResultSet rs = ps.executeQuery();
        ArrayList<Piattaforma> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(new Piattaforma(rs.getString(1)));

        return piattaforme;
    }

    public void doSave(String gioco, ArrayList<Piattaforma> piattaforme) throws SQLException {
        PreparedStatement ps;
        for (Piattaforma p : piattaforme) {
            ps = con.prepareStatement("INSERT INTO gioco_piattaforma VALUES (?,?)");

            ps.setString(1, gioco);
            ps.setString(2, p.getNome());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");
        }
    }
}
