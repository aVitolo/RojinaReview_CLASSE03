package rojinaReview.model.dao;

import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class ProdottoDAO {
    private final Connection con;

    public Connection getCon() {
        return con;
    }

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
            p.setTesto(rs.getString(3));
            p.setImmagine(rs.getString(4));
            p.setPrezzo(rs.getFloat(5));
            p.setQuantità(rs.getInt(6));
            p.setMediaVoto(rs.getFloat(7));
            p.setNumeroVoti(rs.getInt(8));
            p.setCategoria(rs.getString(9))


            return p;
        }

        return null;

    }

    public String doRetrieveNameById(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT nome FROM prodotto WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getString(1);

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
            p.setTesto(rs.getString(3));
            p.setImmagine(rs.getString(4));
            p.setPrezzo(rs.getFloat(5));
            p.setQuantità(rs.getInt(6));
            p.setMediaVoto(rs.getFloat(7));
            p.setNumeroVoti(rs.getInt(8));
            p.setCategoria(rs.getString(9))

            prodotti.add(p);
        }
        return prodotti;
    }

    public ArrayList<Prodotto> updateContent(String offset, String categoria, String ordine) throws SQLException {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        int limit = 12;

        // scelgo gli attributi da prelevare
        String select = " SELECT p.id, p.nome, p.prezzo, p.immagine, p.mediaVoto ";

        // eseguo eventualmente dei join per gestire il filtraggio
        String from =   " FROM prodotto p"+
                (!categoria.equals("Categoria") ?
                        " JOIN prodotto_categoria pc on p.id=pc.prodotto " :
                        " ");

        String where = " WHERE ";
        // aggiungo eventualemente i confronti per il filtraggio
        where +=  (!categoria.equals("Categoria") ?
                " pc.categoria='"+categoria+"'"
                : "" );
        // se non ho apportato nessuna modifica rimouvo WHERE per evitare una scorretta formatazzione
        if (where.equals(" WHERE ")) where = " ";

        // imposto il parametro secondo il quale ordinare l'output + limite e offset
        String order =  " ORDER BY ";
        order+=  (ordine.equals("Highest Vote") ? " p.mediaVoto DESC" :
                    (ordine.equals("Lowest Vote") ? "p.mediaVoto ASC" :
                        (ordine.equals("Least Recent")? " p.id ASC " :
                            (ordine.equals("Lowest Price")? " p.prezzo ASC " :
                              " p.prezzo DESC " )))) +
             " LIMIT "+limit+" OFFSET "+offset;

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

    public ArrayList<Prodotto> doRetriveAll() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Prodotto");

        while (rs.next()) {
            Prodotto p = new Prodotto();

            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setTesto(rs.getString(3));
            p.setImmagine(rs.getString(4));
            p.setPrezzo(rs.getFloat(5));
            p.setQuantità(rs.getInt(6));
            p.setMediaVoto(rs.getFloat(7));
            p.setNumeroVoti(rs.getInt(8));
            p.setCategoria(rs.getString(9))

            list.add(p);
        }
        return list;
    }

    //non serve?
    public void doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Prodotto WHERE id=?");
        ps.setInt(1,Id);
        ps.executeUpdate();
    }

    public void doUpdate(Prodotto prodotto) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("UPDATE Prodotto SET nome=?,descrizione=?,immagine=?,prezzo=?,disponibilità=?,nome_categoria=? WHERE id=?");
        ps.setString(1,prodotto.getNome());
        ps.setString(2,prodotto.getDescrizione());
        ps.setString(3,prodotto.getImmagine());
        ps.setFloat(4,prodotto.getPrezzo());
        ps.setInt(5,prodotto.getQuantità());
        ps.setString(6,prodotto.getCategoria());
        ps.setInt(7,prodotto.getId());
        ps.executeUpdate();
    }
    }

    public void doSave(Prodotto prodotto) throws SQLException{
        PreparedStatement ps =
                con.prepareStatement("INSERT INTO Prodotto (nome,descrizione,immagine,prezzo,disponibilità,nome_categoria,mediaVoto,numeroVoti)" +
                        "VALUES (?, ?, ?, ?, ?, ?,0,0);");
        ps.setString(1,prodotto.getNome());
        ps.setString(2,prodotto.getDescrizione());
        ps.setString(3,prodotto.getImmagine());
        ps.setFloat(4,prodotto.getPrezzo());
        ps.setInt(5,prodotto.getQuantità());
        ps.setString(6,prodotto.getCategoria());
        ps.executeUpdate();
    }
}
