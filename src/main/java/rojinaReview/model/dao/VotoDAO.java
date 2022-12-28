package rojinaReview.model.dao;

import rojinaReview.model.beans.VotoGioco;
import rojinaReview.model.beans.VotoProdotto;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.Voto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class VotoDAO {
    private Connection con;

    public VotoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public VotoDAO(Connection con){
        this.con = con;
    }

    public ArrayList<Voto> doRetrieveByUser(String utente) throws SQLException {
        ArrayList<Voto> voti = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM voto WHERE utente=?");
        ps.setString(1, utente);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            VotoGioco voto = new VotoGioco();
            voto.setGioco(rs.getString(1));
            voto.setUtente(utente);
            voto.setVoto(rs.getFloat(3));
            voto.setDataVotazione(rs.getDate(4));

            voti.add(voto);
        }

        ps = con.prepareStatement("SELECT * FROM gradimento WHERE utente=?");
        ps.setString(1, utente);
        rs = ps.executeQuery();

        while (rs.next())
        {
            VotoProdotto voto = new VotoProdotto();
            voto.setId(rs.getInt(1));
            voto.setUtente(utente);
            voto.setVoto(rs.getFloat(3));
            voto.setDataVotazione(rs.getDate(4));

            voti.add(voto);
        }

        voti.sort(Comparator.comparing(v -> v.getDataVotazione()));

        return voti;
    }

    //table Gioco, Prodotto
    public Voto doRetrieveByUserAndIDTable(String user, String id, String table) throws SQLException {
        Voto v = null;
        PreparedStatement ps;
        ResultSet rs;
        if(table.equalsIgnoreCase("gioco"))
        {
            ps = con.prepareStatement("SELECT gioco, utente, voto, dataVotazione FROM voto WHERE gioco=? && utente=?");
            ps.setString(1, id);
            ps.setString(2, user);
            rs = ps.executeQuery();

            if(rs.next()){
                v = new VotoGioco();
                VotoGioco vg = (VotoGioco) v;
                vg.setGioco(rs.getString(1));
                vg.setUtente(rs.getString(2));
                vg.setVoto(rs.getFloat(3));
                vg.setDataVotazione(rs.getDate(4));

            }
        }
        else if(table.equalsIgnoreCase("prodotto"))
        {
            ps = con.prepareStatement("SELECT prodotto, utente, voto, dataVotazione FROM gradimento WHERE prodotto=? && utente=?");
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, user);
            rs = ps.executeQuery();

            if(rs.next()){
                v = new VotoProdotto();
                VotoProdotto vp = (VotoProdotto) v;
                vp.setId(rs.getInt(1));
                vp.setUtente(rs.getString(2));
                vp.setVoto(rs.getFloat(3));
                vp.setDataVotazione(rs.getDate(4));

            }
        }

        return v;
    }

    public void doSave(Voto v, String table) throws SQLException {
        PreparedStatement ps;
        float votoPrecedente = 0;
        int increment = 1;
        if(table.equalsIgnoreCase("gioco"))
        {
            VotoGioco votogioco = (VotoGioco) v;
            //cancellazione voto precedente
            ps = con.prepareStatement("SELECT voto FROM voto WHERE utente=? && gioco=?");
            ps.setString(1, votogioco.getUtente());
            ps.setString(2, votogioco.getGioco());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                increment = 0;
                votoPrecedente = rs.getFloat(1);
            }

            ps = con.prepareStatement("DELETE FROM voto WHERE utente=? && gioco=?");
            ps.setString(1, votogioco.getUtente());
            ps.setString(2, votogioco.getGioco());
            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO voto VALUES(?, ?, ?, ?) ");
            ps.setString(1, votogioco.getGioco());
            ps.setString(2, votogioco.getUtente());
            ps.setFloat(3, votogioco.getVoto());
            ps.setDate(4, votogioco.getDataVotazione());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

            ps = con.prepareStatement("UPDATE gioco SET mediaVoto = (mediaVoto*numeroVoti - ? + ?)/(numeroVoti+?), numeroVoti = numeroVoti + ? WHERE titolo=?");
            ps.setFloat(1, votoPrecedente);
            ps.setFloat(2, votogioco.getVoto());
            ps.setInt(3, increment);
            ps.setInt(4, increment);
            ps.setString(5, votogioco.getGioco());
            ps.executeUpdate();
        }
        else if(table.equalsIgnoreCase("prodotto"))
        {
            VotoProdotto votoprodotto = (VotoProdotto) v;
            //cancellazione voto precedente
            ps = con.prepareStatement("SELECT voto FROM gradimento WHERE utente=? && prodotto=?");
            ps.setString(1, votoprodotto.getUtente());
            ps.setInt(2, votoprodotto.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                increment = 0;
                votoPrecedente = rs.getFloat(1);
            }

            ps = con.prepareStatement("DELETE FROM gradimento WHERE utente=? && gradimento.prodotto=?");
            ps.setString(1, votoprodotto.getUtente());
            ps.setInt(2, votoprodotto.getId());
            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO gradimento VALUES (?, ?, ?, ?)");
            ps.setInt(1, votoprodotto.getId());
            ps.setString(2, votoprodotto.getUtente());
            ps.setFloat(3, votoprodotto.getVoto());
            ps.setDate(4, votoprodotto.getDataVotazione());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");

            ps = con.prepareStatement("UPDATE prodotto SET mediaVoto = (mediaVoto*numeroVoti - ? + ?)/(numeroVoti+?), numeroVoti = numeroVoti + ? WHERE id=?");
            ps.setFloat(1, votoPrecedente);
            ps.setFloat(2, votoprodotto.getVoto());
            ps.setInt(3, increment);
            ps.setInt(4, increment);
            ps.setInt(5, votoprodotto.getId());
            ps.executeUpdate();
        }
    }
}
