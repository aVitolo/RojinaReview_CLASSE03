package model.dao;

import model.beans.Ordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {

    public ArrayList<Ordine> doRetrieveByUser(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, stato, tracking, dataOrdine, totale, pagamento, via, numeroCivico, città, cap FROM ordine WHERE utente=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordine> ordini = new ArrayList<>();
            while(rs.next()) {
                Ordine o = new Ordine();
                o.setId(rs.getInt(1));
                o.setStato(rs.getString(2));
                o.setTracking(rs.getString(3));
                o.setDataOrdine(rs.getDate(4));
                o.setTotale(rs.getFloat(5));
                o.setPagamento(rs.getString(6));
                o.setIndirizzo(rs.getString(7) + " " +
                        rs.getString(8) + " " +
                        rs.getString(9) + " " +
                        rs.getString(10));

                ps = con.prepareStatement("SELECT po.prodotto, po.prezzo, po.quantità FROM prodotto_ordine po join ordine o on po.ordine = o.id WHERE utente=?");
                ps.setString(1, user);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Ordine.ProdottoOrdine p = o.new ProdottoOrdine();
                    p.setProdotto(new ProdottoDAO().doRetriveById(rs.getInt(1)));
                    p.setPrezzo(rs.getFloat(2));
                    p.setQuantita(rs.getInt(3));
                    o.getProdotti().add(p);
                }
                ordini.add(o);
            }
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
