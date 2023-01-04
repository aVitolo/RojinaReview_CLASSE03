package rojinaReview.model.dao;

import rojinaReview.model.beans.*;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {
    private final Connection con;

    public OrdineDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public OrdineDAO(Connection con) {
        this.con = con;
    }

    /*
        Carica tutti gli ordini di un videogiocatore attraverso il suo id
     */
    public ArrayList<Ordine> doRetrieveUserById(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement(
                        "SELECT " +
                                "o.id, " +
                                "o.stato, " +
                                "o.dataOrdine, " +
                                "o.totale, " +
                                "o.numeroCivico_videogiocatore, " +
                                "o.via_videogiocatore, " +
                                "o.città_videogiocatore, " +
                                "o.cap_videogiocatore\t, " +
                                "p.nome, " +
                                "p.cognome," +
                                "p.numeroCarta, " +
                                "p.dataScadenza " +
                                "FROM Ordine o join Pagamento p on o.numeroCarta_pagamento = p.numeroCarta and o.id_videogiocatore = p.id_videogiocatore " +
                                "WHERE o.id_videogiocatore=? " +
                                "ORDER BY o.dataOrdine DESC ");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ResultSet rsInt;
        ArrayList<Ordine> ordini = new ArrayList<>();
        while (rs.next()) {
            Ordine o = new Ordine(
                    new ArrayList<Prodotto>(),
                    rs.getDate(3),
                    rs.getFloat(4),
                    rs.getInt(1),
                    new Indirizzo(rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8)),
                    new Pagamento(rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getDate(12)),
                    rs.getString(2));
        }
        return ordini;
    }

    /*
        Carica tutti i prodotti di un ordine attraverso il suo id
    */
    public ArrayList<Prodotto> doRetrieveByOrderId(int id) throws SQLException {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(
                "SELECT po.id_prodotto, po.prezzoAcquisto, po.quantità " +
                    "FROM Prodotto_Ordine po " +
                    "WHERE po.id_ordine=?");
        ps.setInt(1, id);
        ResultSet rsInt = ps.executeQuery();
        while (rsInt.next()) {
            Prodotto prodotto = (new ProdottoDAO(con).doRetrieveById(rsInt.getInt(1)));
            prodotto.setPrezzo(rsInt.getFloat(2));
            prodotto.setQuantità(rsInt.getInt(3));
            prodotti.add(prodotto);
        }
        return prodotti;
    }
    /*
        Rende Persistente L'ordine
     */
    public void confirmOrder(Ordine o, int idUser, ArrayList<Prodotto> prodottiContext) throws SQLException {
        ArrayList<Prodotto> prodotti = o.getProdotti();
        PreparedStatement ps;
        //cancello i prodotti-carrello
        ps = con.prepareStatement("DELETE FROM Prodotto_Carrello WHERE id_videogiocatore=?");
        ps.setInt(1, idUser);
        ps.executeUpdate();
        //cancello il carrello già presente nel database
        ps = con.prepareStatement("DELETE FROM Carrello WHERE id_videogiocatore=?");
        ps.setInt(1, idUser);
        ps.executeUpdate();

        ps = con.prepareStatement("INSERT INTO Ordine (" +
                "stato, " +
                "dataOrdine, " +
                "totale, " +
                "numeroCarta_pagamento," +
                "id_videogiocatore, " +
                "via_videogiocatore, " +
                "numeroCivico_videogiocatore," +
                " città_videogiocatore, " +
                "cap_videogiocatore) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        ps.setString(1, o.getStato());
        ps.setDate(2, o.getDataOrdine());
        ps.setFloat(3, o.getTotale());
        ps.setString(4, o.getPagamento().getNumeroCarta());
        ps.setInt(5, idUser);
        ps.setString(6, o.getIndirizzo().getVia());
        ps.setInt(7, o.getIndirizzo().getNumeroCivico());
        ps.setString(8, o.getIndirizzo().getCittà());
        ps.setString(9, o.getIndirizzo().getCap());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
        //per prendere l'id dell'ordine appena inserito che servirà in prodotto_ordine
        ps = con.prepareStatement("SELECT id FROM Ordine WHERE id_videogiocatore=? ORDER BY id DESC LIMIT 1");
        ps.setInt(1, idUser);
        ResultSet rs = ps.executeQuery();
        int idOrdine = -1;
        if(rs.next())
            idOrdine = rs.getInt(1);

        for(Prodotto p : prodotti) {
            //controllo se il prodotto è effettivamente disponibile
            ps = con.prepareStatement("SELECT disponibilità FROM Prodotto WHERE id=?");
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();
            if (rs.next())
                if (p.getQuantità() > rs.getInt("disponibilità"))
                    throw new SQLException("Prodotto non disponibile");

            //aggiorno la disponibilità dei prodotti nel database
            ps = con.prepareStatement("UPDATE Prodotto SET disponibilità=disponibilità-? WHERE id=?");
            ps.setInt(1, p.getQuantità());
            ps.setInt(2, p.getId());
            ps.executeUpdate();

            //aggiorno la disponibilità nel context
            if (prodottiContext != null) {
                for (Prodotto pContext : prodottiContext)
                    if (pContext.getId() == p.getId())
                        pContext.setQuantità(pContext.getQuantità() - p.getQuantità());
            }
            ps = con.prepareStatement("INSERT INTO Prodotto_Ordine VALUES (?, ?, ?, ?)");
            ps.setInt(1, p.getId());
            ps.setInt(2, idOrdine);
            ps.setFloat(3, p.getPrezzo());
            ps.setInt(4, p.getQuantità());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

        }


    }

}
