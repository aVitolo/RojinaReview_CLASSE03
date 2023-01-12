package rojinaReview.model.dao;

import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegnalazioneDAO {
    private final Connection con;

    public SegnalazioneDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public SegnalazioneDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Commento> doRetrieveReportedComments() throws SQLException {
        ArrayList<Commento> commenti = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs,rss;

        ps = con.prepareStatement("SELECT c.id,c.testo,v.nickname,c.id_prodotto,c.id_recensione,c.id_notizia,count(id_commento) "+
               "FROM commento c "+
               "JOIN segnalazione s on c.id=s.id_commento "+
               "JOIN videogiocatore v on v.id=c.id_videogiocatore " +
               "GROUP BY s.id_commento ");
        rs = ps.executeQuery();
        while(rs.next()){
            Commento c = new Commento();
            c.setId(rs.getInt(1));
            c.setTesto(rs.getString(2));
            c.setNicknameVideogiocatore(rs.getString(3));
            c.setNumeroSegnalazioni(rs.getInt(7));
            if(rs.getObject(4)!=null){
                ps = con.prepareStatement("SELECT p.nome FROM Prodotto p WHERE  p.id=?");
                ps.setInt(1,rs.getInt(4));
                rss = ps.executeQuery();
                if(rss.next())
                    c.setNomeContenuto(rss.getString(1));
                else{
                    //exception
                }
            }
            else if(rs.getObject(5)!=null){
                System.out.println("Recensione");
                ps = con.prepareStatement("SELECT p.nome FROM Recensione p WHERE  p.id=?");
                ps.setInt(1,rs.getInt(5));
                rss = ps.executeQuery();
                if(rss.next())
                    c.setNomeContenuto(rss.getString(1));
                else{
                    //exception
                }
            }
            else if(rs.getObject(6)!=null){
                System.out.println("Notizia");
                ps = con.prepareStatement("SELECT p.nome FROM Notizia p WHERE  p.id=?");
                ps.setInt(1,rs.getInt(6));
                rss = ps.executeQuery();
                    if(rss.next())
                        c.setNomeContenuto(rss.getString(1));
                    else{
                        //exception
                    }
            }
            else{
                //exception
            }
            commenti.add(c);
        }

        /*
        //prodotto
        ps = con.prepareStatement("" +
                "SELECT c.id, c.testo, c.id_videogiocatore, v.nickname, c.id_prodotto, p.nome " +
                "FROM commento c " +
                "JOIN videogiocatore v on v.id = c.id_videogiocatore " +
                "JOIN prodotto p on p.id = c.id_prodotto " +
                "JOIN segnalazione s on c.id = s.id_commento and v.id = s.id_videogiocatore");
        rs = ps.executeQuery();
        while(rs.next())
        {
            Commento c = new Commento();

            c.setId(rs.getInt(1));
            c.setTesto(rs.getString(2));
            c.setIdVideogiocatore(rs.getInt(3));
            c.setNicknameVideogiocatore(rs.getString(4));
            c.setIdContenuto(rs.getInt(5));
            c.setNomeContenuto(rs.getString(6));
            c.setTipo(0); //prodotto

            commenti.add(c);
        }
        //recensione
        ps = con.prepareStatement("" +
                "SELECT c.id, c.testo, c.id_videogiocatore, v.nickname, c.id_recensione, r.nome " +
                "FROM commento c JOIN videogiocatore v on v.id = c.id_videogiocatore " +
                "JOIN recensione r on r.id = c.id_recensione " +
                "JOIN segnalazione s on c.id = s.id_commento and v.id = s.id_videogiocatore");
        rs = ps.executeQuery();
        while(rs.next())
        {
            Commento c = new Commento();

            c.setId(rs.getInt(1));
            c.setTesto(rs.getString(2));
            c.setIdVideogiocatore(rs.getInt(3));
            c.setNicknameVideogiocatore(rs.getString(4));
            c.setIdContenuto(rs.getInt(5));
            c.setNomeContenuto(rs.getString(6));
            c.setTipo(1); //recensione

            commenti.add(c);
        }
        //notizia
        ps = con.prepareStatement("" +
                "SELECT c.id, c.testo, c.id_videogiocatore, v.nickname, c.id_notizia, n.nome " +
                "FROM commento c " +
                "JOIN videogiocatore v on v.id = c.id_videogiocatore " +
                "JOIN notizia n on n.id = c.id_notizia " +
                "JOIN segnalazione s on c.id = s.id_commento and v.id = s.id_videogiocatore");
        rs = ps.executeQuery();
        while(rs.next())
        {
            Commento c = new Commento();

            c.setId(rs.getInt(1));
            c.setTesto(rs.getString(2));
            c.setIdVideogiocatore(rs.getInt(3));
            c.setNicknameVideogiocatore(rs.getString(4));
            c.setIdContenuto(rs.getInt(5));
            c.setNomeContenuto(rs.getString(6));
            c.setTipo(2); //notizia

            commenti.add(c);
        }
      */
        return commenti;

    }

    public ArrayList<Segnalazione> doRetrieveReportsForComment(int commento) throws SQLException {
        ArrayList<Segnalazione> segnalazioni = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("" +
                "SELECT s.id_commento, s.id_videogiocatore, s.motivazione, s.commentoAggiuntivo, s.dataSegnalazione, v.nickname " +
                "FROM segnalazione s " +
                "JOIN videogiocatore v on v.id = s.id_videogiocatore");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Segnalazione s = new Segnalazione();

            s.setId_commento(rs.getInt(1)); //rimuovibile
            s.setId_videogiocatoreSegnalante(rs.getInt(2));
            s.setMotivo(rs.getString(3));
            s.setCommentoAggiuntivo(rs.getString(4));
            s.setDataSegnalazione(rs.getDate(5));
            s.setVideogiocatoreSegnalante(rs.getString(6));

            segnalazioni.add(s);
        }
            return segnalazioni;

    }

    public void deleteReports(int commento) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM segnalazione WHERE id_commento=?");
        ps.setInt(1, commento);
        ps.executeUpdate();
    }

    public void doSave(Segnalazione segnalazione) throws SQLException {
        PreparedStatement ps=con.prepareStatement("INSERT INTO Segnalazione VALUES (?,?,?,?,?)");
        ps.setInt(1,segnalazione.getId_commento());
        ps.setInt(2,segnalazione.getId_videogiocatoreSegnalante());
        ps.setString(3,segnalazione.getMotivo());
        ps.setString(4,segnalazione.getCommentoAggiuntivo());
        ps.setDate(5,segnalazione.getDataSegnalazione());
        ps.executeUpdate();
    }




}
