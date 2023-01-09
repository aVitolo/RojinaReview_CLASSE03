package rojinaReview.opinione.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.opinione.service.OpinioneServiceImpl;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "inviaSegnalazioneServlet", value = "/inviaSegnalazioneServlet")

public class inviaSegnalazioneServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("id_commento"));
        System.out.println(request.getParameter("motivo"));
        System.out.println(request.getParameter("commento_aggiuntivo"));
        int flag= Integer.parseInt(request.getParameter("flag"));
        java.sql.Date date= new java.sql.Date(System.currentTimeMillis());
        Videogiocatore utente= (Videogiocatore) request.getSession().getAttribute("videogiocatore");
        Segnalazione segnalazione=new Segnalazione();
        OpinioneServiceImpl opinioneService=null;
        String address=null;
        if(flag==0){
            address="/Rojina_Review_war/getResource?type=shop&id="+request.getParameter("id_contenuto");
        }
        else if (flag==1){
            address="/Rojina_Review_war/getResource?type=reviews&id="+request.getParameter("id_contenuto");
        }
        else if(flag==2) {
            address = "/Rojina_Review_war/getResource?type=news&id=" + request.getParameter("id_contenuto");
        }
        try {
            opinioneService=new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        segnalazione.setId_commento(Integer.parseInt(request.getParameter("id_commento")));
        segnalazione.setId_videogiocatoreSegnalante(utente.getId());
        segnalazione.setVideogiocatoreSegnalante(utente.getNickname());
        segnalazione.setMotivo(request.getParameter("motivo"));
        segnalazione.setCommentoAggiuntivo(request.getParameter("commento_aggiuntivo"));
        segnalazione.setDataSegnalazione(date);
        try {
            opinioneService.inviaSegnalazione(segnalazione);
        } catch (InsertOpinionException e) {
            e.printStackTrace();
        }
        response.sendRedirect(address);
    }
}
