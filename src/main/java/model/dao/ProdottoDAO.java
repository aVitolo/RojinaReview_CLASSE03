package model.dao;

import model.beans.Prodotto;
import model.beans.Recensione;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {
    private final Connection con;

    public ProdottoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public ProdottoDAO(Connection con) {
        this.con = con;
    }

    public Prodotto doRetrieveById(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM prodotto WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Prodotto p = new Prodotto();
        if (rs.next()) {
            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setDescrizione(rs.getString(3));
            p.setDisponibilità(rs.getInt(4));
            p.setPrezzo(rs.getFloat(5));
            p.setImmagine(rs.getString(6));

            if (rs.getInt(7) == 1) {
                ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                ps.setInt(1, id);
                ResultSet r = ps.executeQuery();
                if (r.next()) {
                    p.getSconto().setNome(r.getString(1));
                    p.getSconto().setPercentuale(r.getFloat(2));
                }
            }

            p.setCategorie(new CategoriaDAO(con).doRetrieveByProductId(id));
            p.setMediaVoto(rs.getFloat(8));
            p.setNumeroVoti(rs.getInt(9));


            return p;
        }

        return null;

    }


    public ArrayList<Prodotto> doRetrieveLast() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM prodotto ORDER BY id DESC LIMIT 12");
        ResultSet rs = ps.executeQuery();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        while (rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setDescrizione(rs.getString(3));
            p.setDisponibilità(rs.getInt(4));
            p.setPrezzo(rs.getFloat(5));
            p.setImmagine(rs.getString(6));

            if (rs.getInt(7) == 1) {
                ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                ps.setInt(1, p.getId());
                ResultSet r = ps.executeQuery();
                if (r.next()) {
                    p.getSconto().setNome(r.getString(1));
                    p.getSconto().setPercentuale(r.getFloat(2));
                }
            }

            p.setCategorie(new CategoriaDAO(con).doRetrieveByProductId(p.getId()));
            p.setMediaVoto(rs.getFloat(8));
            p.setNumeroVoti(rs.getInt(9));

            prodotti.add(p);
        }
        return prodotti;
    }

    public ArrayList<Prodotto> updateContent(int lastID, String reset, String categoria, String ordine) throws SQLException {
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        // scelgo gli attributi da prelevare
        String select = " SELECT p.id, p.nome, p.prezzo, p.immagine, p.mediaVoto ";

        // eseguo eventualmente dei join per gestire il filtraggio
        String from =   " FROM prodotto p"+
                (!categoria.equals("Categoria") ? " JOIN prodotto_categoria pc on p.id=pc.prodotto " : " ");

        String where = " WHERE ";
        /*
            eseguo eventualmente il confrono con gli attributi passati tramite request
            request = yes -> devo filtrare quindi prendo i primi 12 secondo il filtraggio impostato
            request = no ->  devo caricare onScroll quindi secondo i parametri di filtraggio quindi devo prelevare da dove mi ero fermato
            ! where.equals(" WHERE ") eseguito per verificare se il controllo precedente e' avvenuto o meno impendo una scorretta formattazione della query
         */
        where +=       (!categoria.equals("Categoria") ? " pc.categoria='"+categoria+"'" : "" );
        where +=       (!reset.equals("yes") ? (lastID != -1 ? (where.equals(" WHERE ") ? " p.id < "+String.valueOf(lastID) :" AND p.id < "+String.valueOf(lastID)) : (where.equals(" WHERE ") ? " p.id >"+String.valueOf(lastID) :" AND p.id > "+String.valueOf(lastID))) : "");

        // se non ho apportato nessuna modifica rimouvo WHERE per evitare una scorretta formatazzione

        if (where.equals(" WHERE ")) where = " ";

        // imposto il parametro secondo il quale ordinare l'output

        String order =  " ORDER BY ";
        order+=  (ordine.equals("Higher Vote") ? " p.mediaVoto DESC" : (ordine.equals("Lower Vote") ? "p.mediaVoto ASC" : (ordine.equals("Least Recent")? " p.id ASC " : " p.id DESC "))) +
                " LIMIT 12 ";

        PreparedStatement ps =
                con.prepareStatement(select + from + where + order);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setPrezzo(rs.getFloat(3));;
            p.setImmagine(rs.getString(4));
            p.setMediaVoto(rs.getFloat(5));
            prodotti.add(p);
        }

        return prodotti;
    }
}
