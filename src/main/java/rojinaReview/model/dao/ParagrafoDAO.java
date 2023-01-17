package rojinaReview.model.dao;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class ParagrafoDAO {
    private final Connection con;

    public ParagrafoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public ParagrafoDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Paragrafo> doRetrieveAllByArticle(int article, boolean type) throws SQLException {
        ArrayList<Paragrafo> paragrafi = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        if (type) //recensione
        {
            ps = con.prepareStatement("SELECT id_paragrafo, titolo, testo, immagine FROM paragrafo WHERE id_recensione=?");
            ps.setInt(1, article);
            rs = ps.executeQuery();

            while (rs.next()) {
                Paragrafo p = new Paragrafo();

                p.setId(rs.getInt(1));
                p.setTitolo(rs.getString(2));
                p.setTesto(rs.getString(3));
                p.setImmagine(rs.getString(4));

                paragrafi.add(p);
            }
        } else if (!type) //notizia
        {
            ps = con.prepareStatement("SELECT id_paragrafo, titolo, testo, immagine FROM paragrafo WHERE id_notizia=?");
            ps.setInt(1, article);
            rs = ps.executeQuery();

            while (rs.next()) {
                Paragrafo p = new Paragrafo();

                p.setId(rs.getInt(1));
                p.setTitolo(rs.getString(2));
                p.setTesto(rs.getString(3));
                p.setImmagine(rs.getString(4));

                paragrafi.add(p);
            }
        }

        return paragrafi;
    }

    public void doSave(Paragrafo p, int article, int type) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        if(type == 1) //recensione
        {
            ps = con.prepareStatement("INSERT INTO paragrafo (titolo, testo, immagine, id_recensione) VALUES (?, ?, ?, ?)");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getTesto());
            ps.setString(3, p.getImmagine());
            ps.setInt(4, article);


            try{
                ps.executeUpdate();
            } catch (MysqlDataTruncation e)
            {
                new RecensioneDAO(con).deleteLast();
                throw new SQLException();
            }

        }
        else if(type == 2) //notizia
        {
            ps = con.prepareStatement("INSERT INTO paragrafo (titolo, testo, immagine, id_notizia) VALUES (?, ?, ?, ?)");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getTesto());
            ps.setString(3, p.getImmagine());
            ps.setInt(4, article);

            try{
                ps.executeUpdate();
            } catch (MysqlDataTruncation e)
            {
                throw new SQLException();
            }

        }
    }

    public void doRemoveAll(int article, int type) throws SQLException //1 recensione, 2 notizia
    {
        PreparedStatement ps;
        if (type == 1) //recensione
        {
            ps = con.prepareStatement("DELETE FROM paragrafo WHERE id_recensione=?");
            ps.setInt(1, article);
            ps.executeUpdate();
        }
        else if (type == 2) //notizia
        {
            ps = con.prepareStatement("DELETE FROM paragrafo WHERE id_notizia=?");
            ps.setInt(1, article);
            ps.executeUpdate();
        }
    }

    public void doUpdate(Paragrafo p, int id) throws SQLException {
        PreparedStatement ps;
        if(p.getImmagine() != null)
        {
            ps = con.prepareStatement("UPDATE paragrafo SET titolo=?, testo=?, immagine=? WHERE id_paragrafo=?");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getTesto());
            ps.setString(3, p.getImmagine());
            ps.setInt(4, p.getId());
        }
        else
        {
            ps = con.prepareStatement("UPDATE paragrafo SET titolo=?, testo=? WHERE id_paragrafo=?");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getTesto());
            ps.setInt(3, p.getId());
        }

        ps.executeUpdate();
    }


}


