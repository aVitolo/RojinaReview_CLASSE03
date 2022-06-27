package model.dao;

import model.beans.Indirizzo;
import model.beans.Ordine;
import model.beans.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {
    private Connection con;

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
                            "FROM ordine o join pagamento p on  o.pagamento = p.numeroCarta and o.utente and o.utente = p.utente WHERE o.utente=?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Ordine> ordini = new ArrayList<>();
        while(rs.next()) {
            Ordine o = new Ordine(
                null,
                rs.getDate(4),
                rs.getFloat(5),
                rs.getInt(1),
                new Indirizzo(  rs.getString(8),
                                rs.getString(10),
                                rs.getString(11),
                                rs.getInt(9)),
                new Pagamento(  rs.getString(12),
                                rs.getString(13),
                                rs.getString(14),
                                rs.getDate(15)),
                rs.getString(rs.getString(2)),
                rs.getString(rs.getString(3)));

            ps = con.prepareStatement("SELECT po.prodotto, po.prezzo, po.quantità FROM prodotto_ordine po join ordine o on po.ordine = o.id WHERE utente=?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ordine.ProdottoOrdine p = o.new ProdottoOrdine();
                p.setProdotto(new ProdottoDAO(con).doRetriveById(rs.getInt(1)));
                p.setPrezzoAcquisto(rs.getFloat(2));
                p.setQuantita(rs.getInt(3));
                o.getProdotti().add(p);
            }
            ordini.add(o);

        }

        return ordini;
    }

}
