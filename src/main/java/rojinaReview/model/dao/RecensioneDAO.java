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
            r.setId_Giornalista(rs.getInt(7));
            r.setIdVideogioco(rs.getInt(8));

            return r;
        }

        return null;

    }

    public Recensione doRetrieveByIdVideogioco(int videogame) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM recensione WHERE id_videogioco=?");
        ps.setInt(1, videogame);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Recensione r = new Recensione();

            r.setId(rs.getInt(1));
            r.setNome(rs.getString(2));
            r.setTesto(rs.getString(3));
            r.setImmagine(rs.getString(4));
            r.setDataScrittura(rs.getDate(5));
            r.setVotoGiornalista(rs.getFloat(6));
            r.setId_Giornalista(rs.getInt(7));
            r.setIdVideogioco(rs.getInt(8));

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

    public void doSave(Recensione r, int idGiornalista, String nomeVideogioco) throws SQLException {
        int idVideogioco = 0;
        PreparedStatement ps = con.prepareStatement("SELECT id FROM videogioco WHERE titolo=?");
        ps.setString(1, nomeVideogioco);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            idVideogioco = rs.getInt(1);
        else
            throw new SQLIntegrityConstraintViolationException();

        ps = con.prepareStatement("INSERT INTO recensione " +
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


    public ArrayList<Recensione> updateContent(String piattaforma, String genere, String ordine) throws SQLException {
        ArrayList<Recensione> recensioni = new ArrayList<>();
        int  limit = 12;

        String select = " SELECT r.id, r.nome, r.testo, r.immagine, r.votoGiornalista";
        String from =   " FROM Recensione r";
        from += (!piattaforma.equals("Piattaforma") ?
                    " JOIN Videogioco_Piattaforma vp on r.id_videogioco=vp.id_videogioco " :
                    " " );
        from += (!genere.equals("Genere") ?
                "JOIN Videogioco_Genere vg on r.id_videogioco=vg.id_videogioco "
                    : " ");
        String where = " WHERE ";
        where += (!piattaforma.equals("Piattaforma") ?
                    " vp.piattaforma='"+piattaforma+"'" :
                    "" );
        where += (!genere.equals("Genere") ?
                    (where.equals(" WHERE ") ?
                            "vg.genere='"+genere+"'" :
                            " AND vg.genere='"+genere+"'")
                    : "");
        if (where.equals(" WHERE ")) where = " ";
        String order =  " ORDER BY ";
        order+= (ordine.equals("Highest Vote") ? " r.votoGiornalista DESC" :
                    (ordine.equals("Lowest Vote") ? "r.votoGiornalista ASC" :
                        (ordine.equals("Least Recent")? " r.id ASC " :
                                " r.id DESC ")));
        System.out.println(select + from + where + order);
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
        int article = 0;
        if(rs.next())
            article = rs.getInt(1);
        else
            throw new SQLException();

        return article;
    }

    public String retrieveNome(int idRecensione) {
        return null;
    }
}

