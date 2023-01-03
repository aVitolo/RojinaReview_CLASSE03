package rojinaReview.model.dao;

import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.utilities.ConPool;

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

    public Carrello doRetrieveByUser(int user) throws SQLException {

        //Valutare se effettuare accorpare tramite join le query o lasciare tutto come ora

        Carrello carrello = new Carrello();

        PreparedStatement ps =
                con.prepareStatement("SELECT pc.quantità, pc.id_prodotto FROM prodotto_carrello pc WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        while (rs.next())
        {
            Prodotto p = new ProdottoDAO(con).doRetrieveById(rs.getInt(2));
            p.setQuantità(rs.getInt(1));

            prodotti.add(p);

        }

        float totale = 0;
        ps = con.prepareStatement("SELECT totale FROM carrello WHERE id_videogiocatore=?"); //sommare direttamente l'arraylist?
        ps.setInt(1, user);
        rs = ps.executeQuery();
        if (rs.next())
            totale = rs.getFloat("totale");

        carrello.setProdottiCarrello(prodotti);
        carrello.setTotale(totale);


        return carrello;

    }

    public void doSave(Carrello c, int user) throws SQLException {
        PreparedStatement ps;
        //cancello i prodotti-carrello
        ps = con.prepareStatement("DELETE FROM prodotto_carrello WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ps.executeUpdate();
        //cancello il carrello già presente nel database
        ps = con.prepareStatement("DELETE FROM carrello WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ps.executeUpdate();

        //effettuo l'insert in carrello
        ps = con.prepareStatement("INSERT INTO carrello VALUES (?, ?)");
        ps.setFloat(1, c.getTotale());
        ps.setInt(2, user);

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
        //effettuo l'insert in prodotto-carrello
        for(Prodotto p : c.getProdottiCarrello()){
            ps = con.prepareStatement("INSERT INTO prodotto_carrello VALUES (?, ?, ?)");
            ps.setInt(1, p.getId());
            ps.setInt(2, user);
            ps.setInt(3, p.getQuantità());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

        }

    }

}
