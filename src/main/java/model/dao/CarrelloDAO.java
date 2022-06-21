package model.dao;

import model.beans.Carrello;
import model.beans.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDAO {
    private Connection con;

    public CarrelloDAO() throws SQLException {
        con = ConPool.getConnection();
    }
    public CarrelloDAO(Connection con){
        this.con = con;
    }

    public Carrello doRetrieveByUser(String user) throws SQLException {

        //Valutare se effettuare accorpare tramite join le query o lasciare tutto come ora


        PreparedStatement ps =
                con.prepareStatement("SELECT prodotto FROM prodotto_carrello pc join prodotto p on pc.prodotto = p.id WHERE utente=?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        while (rs.next())
            prodotti.add(new ProdottoDAO(con).doRetriveById(rs.getInt(1)));
        con.prepareStatement("SELECT totale FROM carrello WHERE utente=?");
        ps.setString(1, user);
        rs = ps.executeQuery();
        return new Carrello(prodotti,
                rs.getFloat(1));

    }

}
