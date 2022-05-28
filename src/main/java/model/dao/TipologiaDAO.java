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
    public ArrayList<Tipologia> doRetriveByGame(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT tipologia FROM gioco_tipologia WHERE gioco=?");
            ResultSet rs =  ps.executeQuery();
            ArrayList<Tipologia> tipologie = new ArrayList<>();
            while (rs.next())
                tipologie.add(new Tipologia(rs.getString(1)));
            return tipologie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
