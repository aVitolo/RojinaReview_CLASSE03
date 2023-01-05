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
                    con.prepareStatement("SELECT * FROM recensione WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Recensione r = new Recensione();

            r.setId(rs.getInt(1));
            r.setNome(rs.getString(2));
            r.setTesto(rs.getString(3));
            r.setImmagine(rs.getString(4));
            r.setDataScrittura(rs.getDate(5));
            r.setVotoGiornalista(rs.getFloat(6));


            return r;
        }

        return null;

    }

    public ArrayList<Recensione> doRetrieveLast() throws SQLException{

        ArrayList<Recensione> recensioni = new ArrayList<>();

        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM recensione ORDER BY id DESC LIMIT 12");


        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Recensione r = new Recensione();

            r.setId(rs.getInt(1));
            r.setNome(rs.getString(2));
            r.setTesto(rs.getString(3));
            r.setImmagine(rs.getString(4));
            r.setDataScrittura(rs.getDate(5));
            r.setVotoGiornalista(rs.getFloat(6));

            recensioni.add(r);
        }

        return recensioni;

    }

    //non serve?
    public int doRetrieveIDByGameTitle(String title) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id FROM recensione WHERE id_videogioco=?");
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();

        if(rs.next())
            return rs.getInt(1);

        return 0;
    }

    public ArrayList<Recensione> doRetrieveByIdJournalist(int giornalista) throws SQLException {
        ArrayList<Recensione> recensioni = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT r.id, r.nome, r.testo, r.immagine, r.dataScrittura," +
                "r.votoGiornalista FROM recensione r JOIN giornalista g on r.id_giornalista = g.id " +
                "WHERE r.id_giornalista=? " +
                "ORDER BY r.dataScrittura DESC ");
        ps.setInt(1, giornalista);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            Recensione r = new Recensione();

            r.setId(rs.getInt(1));
            r.setNome(rs.getString(2));
            r.setTesto(rs.getString(3));
            r.setImmagine(rs.getString(4));
            r.setDataScrittura(rs.getDate(5));
            r.setVotoGiornalista(rs.getFloat(6));

            recensioni.add(r);
        }

        return recensioni;
    }

    public void doSave(Recensione r, int idGiornalista, int idVideogioco) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO recensione " +
                "(testo, id_giornalista, id_videogioco, nome, votoGiornalista, dataScrittura, immagine) VALUES(?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, r.getTesto());
        ps.setInt(2, idGiornalista);
        ps.setInt(3, idVideogioco);
        ps.setString(4, r.getNome());
        ps.setFloat(5, r.getVotoGiornalista());
        ps.setDate(6, r.getDataScrittura());
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
            r.setNome(rs.getString(2));
            r.setTesto(rs.getString(3));;
            r.setImmagine(rs.getString(4));
            r.setVotoGiornalista(rs.getFloat(5));
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
            r.setNome(rs.getString(2));
            r.setTesto(rs.getString(3));
            r.setImmagine(rs.getString(4));
            r.setDataScrittura(rs.getDate(5));
            r.setVotoGiornalista(rs.getFloat(6));

            list.add(r);
        }
        return list;
    }

    //non serve?
    public boolean doRemoveById(int Id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Recensione WHERE id=?");
        ps.setInt(1,Id);
        int i = ps.executeUpdate();
        return i == 1;
    }

    public int doRetrieveLastId() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id FROM recensione ORDER BY id DESC LIMIT 1");
        ResultSet rs = ps.executeQuery();
        int article = rs.getInt(1);
        return article;
    }

    public String retrieveNome(int idRecensione) {
        return null;
    }
}

