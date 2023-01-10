package rojinaReview.autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.dao.GiornalistaDAO;
import rojinaReview.model.dao.ManagerDAO;
import rojinaReview.model.exception.AuthorizeException;
import rojinaReview.model.exception.NotAuthorizeException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "gestisciRichiestaServlet", value = "/gestisciRichiestaServlet")
public class gestisciRichiesteServlet extends HttpServlet {

    private String path = "/WEB-INF/results/managerPages/managerRichieste.jsp";
    private AutenticazioneServiceImpl asi;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            asi = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Utente utente;

        String table=request.getParameter("table");
        int action=Integer.parseInt(request.getParameter("action"));
        int id=Integer.parseInt(request.getParameter("id"));

        if(table.equals("Manager")){
            utente = new Manager();
            utente.setId(id);

            if(action==0){
                try {
                    asi.negaRegistrazioneManager((Manager) utente);
                } catch (NotAuthorizeException e) {
                    e.printStackTrace();
                }
            }
            else if(action==1){
                try {
                    asi.autorizzaRegistrazioneManager((Manager) utente);
                } catch (AuthorizeException e) {
                    e.printStackTrace();
                }
            }
            else{
                //action diversa da 0 o 1
            }
        }
        else if(table.equals("Giornalista")){
            utente = new Giornalista();
            utente.setId(id);

            if(action==0){
                try {
                    asi.negaRegistrazioneGiornalista((Giornalista) utente);
                } catch (NotAuthorizeException e) {
                    e.printStackTrace();
                }

            }
            else if(action==1){
                try {
                    asi.autorizzaRegistrazioneGiornalista((Giornalista) utente);
                } catch (AuthorizeException e) {
                    e.printStackTrace();
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
