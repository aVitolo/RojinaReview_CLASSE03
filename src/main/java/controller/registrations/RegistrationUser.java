package controller.registrations;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userCreationCheckServlet", value = "/userCreationCheckServlet")
public class RegistrationUser extends HttpServlet {
    private Utente tmp;
    private String registrationErrata = "./registerUser.jsp";
    private String homePage =  "./home";

    //Info costanti per validazione input
    private final String[] badCharacters = {"#","-"," ","\'","\""};

    private final int passwordMaxLenght = 20;
    private final int passwordMinLenght = 6;

    private final int nickNameMinLenght = 5;
    private final int nickNameMaxLenght = 30;

    private final int emailMinLenght = 5;
    private final int emailMaxLenght = 30;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getSession().getAttribute("utente") != null)
                response.sendRedirect(homePage);
            else {
                //Preleva dalla request l'email
                String email = request.getParameter("email");

                //Inizializza la connessione al DB tramite DAO
                UtenteDAO uDAO = new UtenteDAO();
                this.tmp = uDAO.doRetriveByEmail(email);

                //Verifica se la ricerca dell' utente è andata male e controlla l' email
                if (this.tmp != null || RegistrationUser.textCheck(email,this.emailMinLenght, this.emailMaxLenght, this.badCharacters) == false) {
                //Il flag a false indica che non è necessario continuare i controlli, l'email esiste gia
                    String message = "Invalid Email";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(registrationErrata);
                    dispatcher.forward(request, response);
                }

                String nickname = request.getParameter("nickname");
                this.tmp = uDAO.doRetriveByNickname(nickname);

                //Verifica se la ricerca dell' utente è andata male e controlla il nickname
                if (this.tmp != null || RegistrationUser.textCheck(nickname,this.nickNameMinLenght, this.nickNameMaxLenght, this.badCharacters) == false) {
                    //Il flag a false indica che non è necessario continuare i controlli, il nickname  esiste già
                    String message = "Invalid Nickname";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(registrationErrata);
                    dispatcher.forward(request, response);
                }

                //Prelevo dalla request la password
                String password = request.getParameter("password");

                //Verifico se la password è valida con la funzione passwordCheck
                if(RegistrationUser.textCheck(password,this.passwordMinLenght, this.passwordMaxLenght, this.badCharacters) == false) {
                    String message = "Invalid Password";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(registrationErrata);
                    dispatcher.forward(request, response);
                }
                else{
                    //Utente ha passato tutti i controlli e può essere registrato nel DB
                    String passHash = Utente.calcolaHash(password);
                    Utente tmpUser = new Utente(email,passHash,nickname);

                    UtenteDAO uDao = new UtenteDAO();
                    if(uDao.doInsertUser(tmpUser) == true){
                        request.getSession().setAttribute("utente",tmpUser);
                        response.sendRedirect(homePage);
                    }
                    else{
                        String message = "Some problem occurred";
                        request.setAttribute("message", message);
                        RequestDispatcher dispatcher = request.getRequestDispatcher(registrationErrata);
                        dispatcher.forward(request, response);
                    }
                }


            }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Funzione che verifica se l' input è valido
    private static boolean textCheck(String text, int passwordMinLenght, int passwordMaxLenght, String[] badCharacters ){
        if(text.length() >= passwordMinLenght && text.length() <= passwordMaxLenght){
            for (int i=0; i < badCharacters.length; i++){
                if(text.contains(badCharacters[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}