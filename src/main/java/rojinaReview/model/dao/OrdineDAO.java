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

    public ArrayList<Ordine> doRetrieveByUser(String user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement(
                        "SELECT o.id, stato, o.tracking, o.dataOrdine, o.totale, o.pagamento, o.via, o.numeroCivico, o.città, o.cap, p.nome, p.cognome, p.numeroCarta, p.dataScadenza " +
                                "FROM ordine o join pagamento p on  o.pagamento = p.numeroCarta  and o.utente = p.utente WHERE o.utente=? ORDER BY o.dataOrdine DESC");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        ResultSet rsInt;
        ArrayList<Ordine> ordini = new ArrayList<>();
        while (rs.next()) {
            Ordine o = new Ordine(
                    new ArrayList<Ordine.ProdottoOrdine>(),
                    rs.getDate(4),
                    rs.getFloat(5),
                    rs.getInt(1),
                    new Indirizzo(rs.getString(7),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(8)),
                    new Pagamento(rs.getString(11),
                            rs.getString(12),
                            rs.getString(13),
                            rs.getDate(14)),
                    rs.getString(2),
                    rs.getString(3));

            ps = con.prepareStatement("SELECT po.prodotto, po.prezzo, po.quantità FROM prodotto_ordine po WHERE po.ordine=?");
            ps.setInt(1, o.getId());
            rsInt = ps.executeQuery();
            while (rsInt.next()) {
                Ordine.ProdottoOrdine p = new Ordine.ProdottoOrdine();
                p.setProdotto(new ProdottoDAO(con).doRetrieveById(rsInt.getInt(1)));
                p.setPrezzoAcquisto(rsInt.getFloat(2));
                p.setQuantita(rsInt.getInt(3));
                o.getProdotti().add(p);
            }
            ordini.add(o);

        }

        return ordini;
    }



    public void confirmOrder(Ordine o, String user, ArrayList<Prodotto> prodottiContext) throws SQLException {
        int idOrdine = 0;
        ArrayList<Ordine.ProdottoOrdine> prodotti = o.getProdotti();
        PreparedStatement ps;
        //cancello i prodotti-carrello
        ps = con.prepareStatement("DELETE FROM prodotto_carrello WHERE utente=?");
        ps.setString(1, user);
        ps.executeUpdate();
        //cancello il carrello già presente nel database
        ps = con.prepareStatement("DELETE FROM carrello WHERE utente=?");
        ps.setString(1, user);
        ps.executeUpdate();

        ps = con.prepareStatement("INSERT INTO ordine (stato, tracking, dataOrdine, totale, pagamento, utente, via, numeroCivico, città, cap) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        ps.setString(1, o.getStato());
        ps.setString(2, o.getTracking());
        ps.setDate(3, o.getDataOrdine());
        ps.setFloat(4, o.getTotale());
        ps.setString(5, o.getPagamento().getNumeroCarta());
        ps.setString(6, user);
        ps.setString(7, o.getIndirizzo().getVia());
        ps.setInt(8, o.getIndirizzo().getNumeroCivico());
        ps.setString(9, o.getIndirizzo().getCittà());
        ps.setString(10, o.getIndirizzo().getCap());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
        //per prendere l'id dell'ordine appena inserito che servirà in prodotto_ordine
        ps = con.prepareStatement("SELECT id FROM ordine WHERE utente=? ORDER BY id DESC LIMIT 1");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            idOrdine = rs.getInt(1);

        for(Ordine.ProdottoOrdine p : prodotti) {
            //controllo se il prodotto è effettivamente disponibile
            ps = con.prepareStatement("SELECT disponibilità FROM prodotto WHERE id=?");
            ps.setInt(1, p.getProdotto().getId());
            rs = ps.executeQuery();
            if (rs.next())
                if (p.getQuantita() > rs.getInt("disponibilità"))
                    throw new SQLException("Prodotto non disponibile");

            //aggiorno la disponibilità dei prodotti nel database
            ps = con.prepareStatement("UPDATE prodotto SET disponibilità=disponibilità-? WHERE id=?");
            ps.setInt(1, p.getQuantita());
            ps.setInt(2, p.getProdotto().getId());
            ps.executeUpdate();

            //aggiorno la disponibilità nel context
            if (prodottiContext != null) {
                for (Prodotto pContext : prodottiContext)
                    if (pContext.getId() == p.getProdotto().getId())
                        pContext.setDisponibilità(pContext.getDisponibilità() - p.getQuantita());
            }
            ps = con.prepareStatement("INSERT INTO prodotto_ordine VALUES (?, ?, ?, ?)");
            ps.setInt(1, p.getProdotto().getId());
            ps.setInt(2, idOrdine);
            ps.setFloat(3, p.getPrezzoAcquisto());
            ps.setInt(4, p.getQuantita());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

        }


    }

}
