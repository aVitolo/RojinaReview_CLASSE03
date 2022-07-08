package model.dao;

import model.beans.Carrello;
import model.beans.Prodotto;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDAO {
    private final Connection con;

    public CarrelloDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public CarrelloDAO(Connection con) {
        this.con = con;
    }

    public Carrello doRetrieveByUser(String user) throws SQLException {

        //Valutare se effettuare accorpare tramite join le query o lasciare tutto come ora


        PreparedStatement ps =
                con.prepareStatement("SELECT pc.quantità, pc.prodotto FROM prodotto_carrello pc WHERE utente=?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Carrello.ProdottoCarrello> prodotti = new ArrayList<>();
        while (rs.next())
        {
            Carrello.ProdottoCarrello prodottoCarrello = new Carrello.ProdottoCarrello();
            prodottoCarrello.setQuantità(rs.getInt(1));
            Prodotto p = new ProdottoDAO().doRetrieveById(rs.getInt(2));
            prodottoCarrello.setProdotto(p);
            prodottoCarrello.setPrezzoAttuale(p.getPrezzo());

            prodotti.add(prodottoCarrello);

        }
        float totale = 0;
        con.prepareStatement("SELECT totale FROM carrello WHERE utente=?");
        ps.setString(1, user);
        rs = ps.executeQuery();
        if (rs.next())
            totale = rs.getFloat(1);

        return new Carrello(prodotti, totale);

    }

}
