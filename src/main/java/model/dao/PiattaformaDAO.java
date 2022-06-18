package model.dao;

import model.beans.Categoria;
import model.beans.Piattaforma;
import model.beans.Tipologia;

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

    public ArrayList<Piattaforma> doRetriveByGame(String titolo) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT tipologia FROM gioco_tipologia WHERE gioco=?");
        ResultSet rs =  ps.executeQuery();
        ArrayList<Piattaforma> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(new Piattaforma(rs.getString(1)));

        return piattaforme;
    }

}
