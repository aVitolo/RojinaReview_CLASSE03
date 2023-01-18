package rojinaReview.model.dao.opinioneDAO;


import rojinaReview.utilities.ConPool;
import rojinaReview.model.beans.Parere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class ParereDAO {
    private Connection con;

    public ParereDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public ParereDAO(Connection con){
        this.con = con;
    }

    //tutti i pareri di un videogiocatore
    public ArrayList<Parere> doRetrieveByUser(int utente) throws SQLException {
        ArrayList<Parere> pareri = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM parere WHERE id_videogiocatore=?");
        ps.setInt(1, utente);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Parere p = new Parere();

            p.setId(rs.getInt(1));
            p.setVoto(rs.getFloat(2));
            p.setDataVotazione(rs.getDate(3));

            int id_videogioco = rs.getInt(5);
            int id_prodotto = rs.getInt(6);
            if (id_videogioco != 0)
            {
                p.setIdProdottoORVideogioco(id_videogioco);
                p.setType(3);

            }
            else if (id_prodotto != 0)
            {
                p.setIdProdottoORVideogioco(id_prodotto);
                p.setType(0);
            }


            pareri.add(p);
        }


        pareri.sort(Comparator.comparing(p -> p.getDataVotazione()));

        return pareri;
    }

    //table Gioco, Prodotto
    public Parere doRetrieveUserOpinion(int user, int idProdottoORVideogioco, int type) throws SQLException {
        Parere p = new Parere();
        PreparedStatement ps;
        ResultSet rs;
        if(type==3) //parere su videogioco
        {
            ps = con.prepareStatement("SELECT id, voto, dataVotazione, id_videogioco FROM parere WHERE id_videogioco=? && id_videogiocatore=?");
            ps.setInt(1, idProdottoORVideogioco);
            ps.setInt(2, user);
            rs = ps.executeQuery();

            if(rs.next()){
                p.setId(rs.getInt(1));
                p.setVoto(rs.getFloat(2));
                p.setDataVotazione(rs.getDate(3));
                p.setIdProdottoORVideogioco(rs.getInt(4));
                p.setType(type);

            }
        }
        else if(type==0) //parere su prodotto
        {
            ps = con.prepareStatement("SELECT id, voto, dataVotazione, id_prodotto FROM parere WHERE id_prodotto=? && id_videogiocatore=?");
            ps.setInt(1, idProdottoORVideogioco);
            ps.setInt(2, user);
            rs = ps.executeQuery();

            if(rs.next()){
                p.setId(rs.getInt(1));
                p.setVoto(rs.getFloat(2));
                p.setDataVotazione(rs.getDate(3));
                p.setIdProdottoORVideogioco(rs.getInt(4));
                p.setType(type);

            }
        }

        return p;
    }

    public void doSave(Parere p, int user) throws SQLException {
        PreparedStatement ps;
        float votoPrecedente = 0;
        int increment = 1;
        if(p.getType()==3) //videogioco
        {
            //cancellazione voto precedente
            ps = con.prepareStatement("SELECT voto FROM Parere WHERE id_videogiocatore=? and id_videogioco=?");
            ps.setInt(1, user);
            ps.setInt(2, p.getIdProdottoORVideogioco());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                increment = 0;
                votoPrecedente = rs.getFloat(1);
            }

            ps = con.prepareStatement("DELETE FROM Parere WHERE id_videogiocatore=? and id_videogioco=?");
            ps.setInt(1, user);
            ps.setInt(2, p.getIdProdottoORVideogioco());
            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO Parere (voto, dataVotazione, id_videogiocatore, id_videogioco, id_prodotto) VALUES(?, ?, ?, ?, ?) ");
            ps.setFloat(1, p.getVoto());
            ps.setDate(2, p.getDataVotazione());
            ps.setInt(3, user);
            ps.setInt(4, p.getIdProdottoORVideogioco());
            ps.setNull(5, 0);


            if(ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

            ps = con.prepareStatement("UPDATE videogioco SET mediaVoto = (mediaVoto*numeroVoti - ? + ?)/(numeroVoti+?), numeroVoti = numeroVoti + ? WHERE id=?");
            ps.setFloat(1, votoPrecedente);
            ps.setFloat(2, p.getVoto());
            ps.setInt(3, increment);
            ps.setInt(4, increment);
            ps.setInt(5, p.getIdProdottoORVideogioco());
            ps.executeUpdate();
        }
        else if(p.getType()==0) //prodotto
        {
            //cancellazione voto precedente
            ps = con.prepareStatement("SELECT voto FROM parere WHERE id_videogiocatore=? && id_prodotto=?");
            ps.setInt(1, user);
            ps.setInt(2, p.getIdProdottoORVideogioco());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                increment = 0;
                votoPrecedente = rs.getFloat(1);
            }

            ps = con.prepareStatement("DELETE FROM parere WHERE id_videogiocatore=? && id_prodotto=?");
            ps.setInt(1, user);
            ps.setInt(2, p.getIdProdottoORVideogioco());
            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO parere (voto, dataVotazione, id_videogiocatore, id_videogioco, id_prodotto) VALUES (?, ?, ?, ?, ?)");
            ps.setFloat(1, p.getVoto());
            ps.setDate(2, p.getDataVotazione());
            ps.setInt(3, user);
            ps.setNull(4, 0);
            ps.setInt(5, p.getIdProdottoORVideogioco());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

            ps = con.prepareStatement("UPDATE prodotto SET mediaVoto = (mediaVoto*numeroVoti - ? + ?)/(numeroVoti+?), numeroVoti = numeroVoti + ? WHERE id=?");
            ps.setFloat(1, votoPrecedente);
            ps.setFloat(2, p.getVoto());
            ps.setInt(3, increment);
            ps.setInt(4, increment);
            ps.setInt(5, p.getIdProdottoORVideogioco());
            ps.executeUpdate();
        }
    }


    public Object doRetrieveByUserAndIDTable(int id, String toString, boolean b) {
        return null;
    }
}
