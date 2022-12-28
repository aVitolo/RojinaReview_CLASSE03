package rojinaReview.controller.autenticazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.utilities.Persona;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginUser extends HttpServlet {
    private Utente tmp;
    private String loginErrato = "./userLogin.jsp";
    private String homePage = "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("utente") != null)
                response.sendRedirect(homePage);
            else {
                //Preleva dalla request l'email
                String email = request.getParameter("email");

                //Inizializza la connessione al DB tramite DAO
                UtenteDAO uDAO = new UtenteDAO();
                this.tmp = uDAO.doRetriveByEmail(email);

                //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
                if (this.tmp == null) {
                    //Il flag a false indica che non è necessario continuare i controlli, l' utente non c' è nel db
                    String message = "Invalid email";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                    dispatcher.forward(request, response);
                }
                else {
                    //Preleva dalla request la password
                    String password = request.getParameter("password");

                    //Calcola Hash della password utente
                    password = Persona.calcolaHash(password);

                    //ottengo la password dell' user nel db
                    String dbPass = this.tmp.getPassword();
                    //confronta le password
                    if (password.equals(dbPass)) {
                        if (this.tmp.getImmagine() == null)
                            this.tmp.setImmagine("./images/utility/defaultImageUser.png"); //immagine di default utente
                        //merge dei carrelli
                        Carrello ospite = (Carrello) session.getAttribute("ospite");
                        this.tmp.setCarrello(LoginUser.mergeCarts(this.tmp.getCarrello(), ospite));
                        session.setAttribute("utente", this.tmp);
                        //rimozione del carrello ospite
                        session.removeAttribute("ospite");
                        response.sendRedirect(homePage);
                    } else {
                        String message = "Invalid password";
                        request.setAttribute("message", message);
                        RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                        dispatcher.forward(request, response);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Carrello mergeCarts(Carrello carrello, Carrello ospite) {
        if(ospite!=null) {
            int i, j;
            boolean trovato;
            ArrayList<Carrello.ProdottoCarrello> prodottiDB = carrello.getProdotti();
            ArrayList<Carrello.ProdottoCarrello> prodottiOspite = ospite.getProdotti();
            for (i = 0; i < prodottiOspite.size(); i++) {
                for (j = 0, trovato = false; j < prodottiDB.size() && !trovato; j++) {
                    if (prodottiOspite.get(i).getProdotto().getId() == prodottiDB.get(j).getProdotto().getId()) {
                        prodottiDB.get(j).setQuantità(prodottiDB.get(j).getQuantità() + prodottiOspite.get(i).getQuantità());
                        trovato = true;
                    }
                }
                //prodotto del carrello ospite non trovato nel DB
                if (!trovato)
                    prodottiDB.add(prodottiOspite.get(i));
            }

            carrello.setProdotti(prodottiDB); //istruzione penso inutile
            carrello.setTotale(carrello.getTotale() + ospite.getTotale());
        }
        return carrello;
    }
}
