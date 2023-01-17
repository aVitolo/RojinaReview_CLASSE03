package rojinaReview.model.dao;

import rojinaReview.model.beans.*;
import rojinaReview.utilities.ConPool;

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

    //usata per lo storico ordini
    public ArrayList<Ordine> doRetrieveByUserId(int user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT o.id, o.stato, o.dataOrdine, o.totale, p.nome, p.cognome, p.numeroCarta, p.dataScadenza, i.via, i.città, i.cap, i.numeroCivico  FROM ordine o JOIN pagamento p on o.id_videogiocatore = p.id_videogiocatore and o.numeroCarta_pagamento = p.numeroCarta JOIN indirizzo i on o.id_videogiocatore = i.id_videogiocatore and o.via_videogiocatore = i.via and o.numeroCivico_videogiocatore = i.numeroCivico and o.città_videogiocatore = i.città and o.cap_videogiocatore = i.cap WHERE o.id_videogiocatore = ? ORDER BY o.dataOrdine DESC");
        ps.setInt(1, user);
        ResultSet rs = ps.executeQuery();
        ResultSet rsInt;
        ArrayList<Ordine> ordini = new ArrayList<>();
        while (rs.next()) {
            Ordine o = new Ordine();

            o.setId(rs.getInt(1));
            o.setStato(rs.getString(2));
            o.setDataOrdine(rs.getDate(3));
            o.setTotale(rs.getFloat(4));
            o.setPagamento(new Pagamento(rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
            o.setIndirizzo(new Indirizzo(rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            o.setProdotti(new ArrayList<>());

            //o.setProdotti(); //settare i prodotti non in questo metodo ma bensì nell'implementazione dell'interfaccia quando si va nei dettagli dell'ordine?

            ps = con.prepareStatement("SELECT po.id_prodotto, p.nome, p.immagine, po.prezzoAcquisto, p.nome_categoria, po.quantità FROM prodotto_ordine po JOIN prodotto p on po.id_prodotto = p.id WHERE po.id_ordine=?");
            ps.setInt(1, o.getId());
            rsInt = ps.executeQuery();
            while (rsInt.next()) {
                Prodotto p = new Prodotto();

                p.setId(rsInt.getInt(1));
                p.setNome(rsInt.getString(2));
                p.setImmagine(rsInt.getString(3));
                p.setPrezzo(rsInt.getFloat(4));
                p.setCategoria(rsInt.getString(5));
                p.setQuantità(rsInt.getInt(6));

                o.getProdotti().add(p);
            }

            ordini.add(o);

        }

        return ordini;
    }



    public void confirmOrder(Ordine o, int user) throws SQLException {
        int idOrdine = 0;
        ArrayList<Prodotto> prodotti = o.getProdotti();
        PreparedStatement ps;
        //cancello i prodotti-carrello
        ps = con.prepareStatement("DELETE FROM prodotto_carrello WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ps.executeUpdate();
        //cancello il carrello già presente nel database
        ps = con.prepareStatement("DELETE FROM carrello WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ps.executeUpdate();

        ps = con.prepareStatement("INSERT INTO ordine (stato, dataOrdine, totale, numeroCarta_pagamento, id_videogiocatore, via_videogiocatore, numeroCivico_videogiocatore, città_videogiocatore, cap_videogiocatore) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        ps.setString(1, o.getStato());
        ps.setDate(2, o.getDataOrdine());
        ps.setFloat(3, o.getTotale());
        ps.setString(4, o.getPagamento().getNumeroCarta());
        ps.setInt(5, user);
        ps.setString(6, o.getIndirizzo().getVia());
        ps.setInt(7, o.getIndirizzo().getNumeroCivico());
        ps.setString(8, o.getIndirizzo().getCittà());
        ps.setString(9, o.getIndirizzo().getCap());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
        //per prendere l'id dell'ordine appena inserito che servirà in prodotto_ordine
        ps = con.prepareStatement("SELECT id FROM ordine WHERE id_videogiocatore=? ORDER BY id DESC LIMIT 1");
        ps.setInt(1, user);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            idOrdine = rs.getInt(1);

        for(Prodotto p : prodotti) {
            //controllo se il prodotto è effettivamente disponibile
            ps = con.prepareStatement("SELECT disponibilità FROM prodotto WHERE id=?");
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();
            if (rs.next())
                if (p.getQuantità() > rs.getInt("disponibilità"))
                    throw new SQLException("Prodotto non disponibile");

            //aggiorno la disponibilità dei prodotti nel database
            ps = con.prepareStatement("UPDATE prodotto SET disponibilità=disponibilità-? WHERE id=?");
            ps.setInt(1, p.getQuantità());
            ps.setInt(2, p.getId());
            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO prodotto_ordine VALUES (?, ?, ?, ?)");
            ps.setInt(1, p.getId());
            ps.setInt(2, idOrdine);
            ps.setFloat(3, p.getPrezzo());
            ps.setInt(4, p.getQuantità());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

        }


    }

}
