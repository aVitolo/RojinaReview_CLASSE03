package rojinaReview.opinione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getReportedCommentDetails", value = "/getReportedCommentDetails")

public class getReportedCommentDetails extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Segnalazione> segnalazioni=new ArrayList<>();
        Commento commento=new Commento();
        int idCommento= Integer.parseInt(request.getParameter("idCommento"));
        System.out.println(idCommento);
        OpinioneServiceImpl opinioneService=null;
        try {
            opinioneService=new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            segnalazioni=opinioneService.visualizzaDettagliCommentoSegnalato(idCommento);
        } catch (LoadingCommentException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/Rojina_Review_war/dettagli.jsp").forward(request,response);
    }
}
