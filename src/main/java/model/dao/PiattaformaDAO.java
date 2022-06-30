package model.dao;

import model.beans.Piattaforma;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PiattaformaDAO {
    private Connection con;

    public PiattaformaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public PiattaformaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Piattaforma> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT DISTINCT piattaforma FROM gioco_piattaforma");
        ResultSet rs = ps.executeQuery();
        ArrayList <Piattaforma> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(new Piattaforma(rs.getString(1)));
        return piattaforme;
    }

    public ArrayList<Piattaforma> doRetriveByGame(String titolo) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT piattaforma FROM gioco_piattaforma WHERE gioco=?");
        ps.setString(1, titolo);
        ResultSet rs =  ps.executeQuery();
        ArrayList<Piattaforma> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(new Piattaforma(rs.getString(1)));

        return piattaforme;
    }

}
