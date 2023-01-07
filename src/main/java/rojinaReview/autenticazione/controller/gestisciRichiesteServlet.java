package rojinaReview.autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.dao.GiornalistaDAO;
import rojinaReview.model.dao.ManagerDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "gestisciRichiestaServlet", value = "/gestisciRichiestaServlet")
public class gestisciRichiesteServlet extends HttpServlet {

    private String path = "/WEB-INF/results/managerPages/managerRichieste.jsp";
    private AutenticazioneServiceImpl asi = new AutenticazioneServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table=request.getParameter("table");
        int action=Integer.parseInt(request.getParameter("action"));
        int id=Integer.parseInt(request.getParameter("id"));
        if(table.equals("Manager")){
            if(action==0){
                try {
                    new ManagerDAO().doRemoveFromQueeue(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(action==1){
                try {
                    new ManagerDAO().doVerificaManager(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                //action diversa da 0 o 1
            }
        }
        else if(table.equals("Giornalista")){
            if(action==0){
                try {
                    new GiornalistaDAO().doRemoveFromQueeue(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(action==1){
                try {
                    new GiornalistaDAO().doVerificaGiornalista(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                //action diversa da 0 o 1
            }
        }
        else {
            //table diversa da manager o giornalista
        }
    }
}
