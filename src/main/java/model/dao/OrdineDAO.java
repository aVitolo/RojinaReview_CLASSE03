package model.dao;

import model.beans.Indirizzo;
import model.beans.Ordine;
import model.beans.Pagamento;
import model.utilities.ConPool;

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
                                "FROM ordine o join pagamento p on  o.pagamento = p.numeroCarta  and o.utente = p.utente WHERE o.utente=?");
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

            ps = con.prepareStatement("SELECT po.prodotto, po.prezzo, po.quantità FROM prodotto_ordine po join ordine o on po.ordine = o.id WHERE utente=?");
            ps.setString(1, user);
            rsInt = ps.executeQuery();
            while (rsInt.next()) {
                Ordine.ProdottoOrdine p = o.new ProdottoOrdine();
                p.setProdotto(new ProdottoDAO(con).doRetrieveById(rsInt.getInt(1)));
                p.setPrezzoAcquisto(rsInt.getFloat(2));
                p.setQuantita(rsInt.getInt(3));
                o.getProdotti().add(p);
            }
            ordini.add(o);

        }

        return ordini;
    }

}
