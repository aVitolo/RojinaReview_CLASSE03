package model.dao;

import model.beans.Categoria;
import model.beans.Gioco;
import model.beans.Piattaforma;
import model.beans.Tipologia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipologiaDAO {
    private Connection con;

    public TipologiaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public TipologiaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Tipologia> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("Select nome FROM tipologia");
        ResultSet rs = ps.executeQuery();
        ArrayList<Tipologia> tipologie = new ArrayList<>();
        while(rs.next())
            tipologie.add(new Tipologia(rs.getString(1)));

        return tipologie;
    }

    public ArrayList<Tipologia> doRetriveByGame(String titolo) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT tipologia FROM gioco_tipologia WHERE gioco=?");
        ps.setString(1, titolo);
        ResultSet rs =  ps.executeQuery();
        ArrayList<Tipologia> tipologie = new ArrayList<>();
        while (rs.next())
                tipologie.add(new Tipologia(rs.getString(1)));

        return tipologie;

    }

}
