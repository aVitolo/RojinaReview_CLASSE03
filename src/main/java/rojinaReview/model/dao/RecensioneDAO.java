package rojinaReview.model.dao;

import rojinaReview.model.beans.Recensione;
import rojinaReview.model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class RecensioneDAO {
    private Connection con;

    public Connection getCon() {
        return con;
    }

    public RecensioneDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public RecensioneDAO(Connection con) {
        this.con = con;
    }

    public Recensione doRetrieveById(int id) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT g.nome, g.cognome, g.immagine, r.id, r.titolo, r.testo, r.voto, r.DataCaricamento, r.gioco, r.immagine FROM recensione r JOIN giornalista g on g.id = r.giornalista WHERE r.id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Recensione r = new Recensione();
            r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            r.setImmagineGiornalista(rs.getString(3));
            r.setId(rs.getInt(4));
            r.setTitolo(rs.getString(5));
            r.setTesto(rs.getString(6));
            r.setVoto(rs.getFloat(7));
            r.setDataCaricamento(rs.getDate(8));
            r.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(9)));
            r.setImmagine(rs.getString(10));
            r.setCommenti(new CommentoDAO(con).getCommentById(r.getId(),"Recensione"));
            return r;
        }

        return null;

    }

    public ArrayList<Recensione> doRetrieveLast() throws SQLException{

        ArrayList<Recensione> recensioni = new ArrayList<>();

        PreparedStatement ps =
                con.prepareStatement("SELECT g.nome, g.cognome, g.immagine ,r.id, r.titolo, r.testo, r.voto, r.DataCaricamento, r.gioco, r.immagine " +
                                         "FROM recensione r JOIN giornalista g on g.id = r.giornalista " +
                                         "ORDER BY r.id DESC LIMIT 12");


        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Recensione r = new Recensione();
            r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            r.setImmagineGiornalista(rs.getString(3));
            r.setId(rs.getInt(4));
            r.setTitolo(rs.getString(5));
            r.setTesto(rs.getString(6));
            r.setVoto(rs.getFloat(7));
            r.setDataCaricamento(rs.getDate(8));
            r.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(9)));
            r.setImmagine(rs.getString(10));
            r.setCommenti(new CommentoDAO(con).getCommentById(r.getId(),"Recensione"));
            recensioni.add(r);
        }

        return recensioni;

    }

    public int doRetrieveIDByGameTitle(String title) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id FROM recensione WHERE gioco=?");
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();

        if(rs.next())
            return rs.getInt(1);

        return 0;
    }

    public ArrayList<Recensione> doRetrieveByIdJournalist(int id) throws SQLException {
        ArrayList<Recensione> recensioni = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT g.nome, g.cognome, r.id, r.titolo, r.testo, r.voto, r.dataCaricamento," +
                "r.gioco, r.immagine FROM recensione r JOIN giornalista g on r.giornalista = g.id " +
                "WHERE r.giornalista=? " +
                "ORDER BY r.dataCaricamento DESC ");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            Recensione r = new Recensione();
            r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
            r.setId(rs.getInt(3));
            r.setTitolo(rs.getString(4));
            r.setTesto(rs.getString(5));
            r.setVoto(rs.getFloat(6));
            r.setDataCaricamento(rs.getDate(7));
            r.setGioco(new GiocoDAO(con).doRetrieveByTitle(rs.getString(8)));
            r.setImmagine(rs.getString(9));
            r.setCommenti(new CommentoDAO(con).getCommentById(r.getId(),"Recensione"));
            recensioni.add(r);
        }

        return recensioni;
    }

    public void doSave(Recensione r, int idGiornalista) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO recensione " +
                "(testo, giornalista, gioco, titolo, voto, dataCaricamento, immagine) VALUES(?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, r.getTesto());
        ps.setInt(2, idGiornalista);
        ps.setString(3, r.getGioco().getTitolo());
        ps.setString(4, r.getTitolo());
        ps.setFloat(5, r.getVoto());
        ps.setDate(6, r.getDataCaricamento());
        ps.setString(7, r.getImmagine());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");


    }


    public ArrayList<Recensione> updateContent(String offset, String piattaforma, String tipologia, String ordine) throws SQLException {
        ArrayList<Recensione> recensioni = new ArrayList<>();
        int  limit = 12;

        String select = " SELECT r.id, r.titolo, r.testo, r.immagine, r.voto ";
        String from =   " FROM recensione r";
        from += (!piattaforma.equals("Piattaforma") ?
                    " JOIN gioco_piattaforma gp on r.gioco=gp.gioco " :
                    " " );
        from += (!tipologia.equals("Tipologia") ?
                    "JOIN gioco_tipologia gt on r.gioco=gt.gioco "
                    : " ");
        String where = " WHERE ";
        where += (!piattaforma.equals("Piattaforma") ?
                    " gp.piattaforma='"+piattaforma+"'" :
                    "" );
        where += (!tipologia.equals("Tipologia") ?
                    (where.equals(" WHERE ") ?
                            "gt.tipologia='"+tipologia+"'" :
                            " AND gt.tipologia='"+tipologia+"'")
                    : "");
        if (where.equals(" WHERE ")) where = " ";
        String order =  " ORDER BY ";
        order+= (ordine.equals("Highest Vote") ? " r.voto DESC" :
                    (ordine.equals("Lowest Vote") ? "r.voto ASC" :
                        (ordine.equals("Least Recent")? " r.id ASC " :
                                " r.id DESC "))) +

                " LIMIT "+ limit +" OFFSET "+ offset;

        PreparedStatement ps =
                con.prepareStatement(select + from + where + order);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Recensione r = new Recensione();
            r.setId(rs.getInt(1));
            r.setTitolo(rs.getString(2));
            r.setTesto(rs.getString(3));;
            r.setImmagine(rs.getString(4));
            r.setVoto(rs.getFloat(5));
            recensioni.add(r);
        }

        return recensioni;
    }

    public ArrayList<Recensione> doRetriveAll() throws SQLException {
        ArrayList<Recensione> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Recensione");

        while(rs.next()){
            Recensione r = new Recensione();
            r.setId(rs.getInt(1));
            r.setTitolo(rs.getString(5));
            r.setTesto(rs.getString(2));
            r.setVoto(rs.getFloat(6));
            r.setDataCaricamento(rs.getDate(7));
            list.add(r);
        }
        return list;
    }

    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Recensione WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }
}

