import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.autenticazioneDAO.VideogiocatoreDAO;
import rojinaReview.model.exception.EmailNotAvailableException;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.NicknameNotAvailableException;
import rojinaReview.registrazione.controller.RegistrationVideogamerServlet;
import rojinaReview.registrazione.service.RegistrazioneService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistationVideogamerServletTest {

    @BeforeClass @BeforeAll
    public static void deleteDuplicates() throws SQLException {
        VideogiocatoreDAO vDAO=new VideogiocatoreDAO();

        vDAO.doRemoveByEmail("test@gmail.com");
    }

    @Test
    @Order(1)
    public void testRegistrazioneCorretta() throws IOException, ServletException, InvalidTextException, NicknameNotAvailableException, EmailNotAvailableException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);
        RegistrazioneService rs=mock(RegistrazioneService.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("test@gmail.com");
        when(request.getParameter("nickname")).thenReturn("testt");
        when(request.getParameter("password")).thenReturn("testtesttest");
        when(request.getRequestDispatcher("./registerUser.jsp")).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new RegistrationVideogamerServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("registrazioneOK"));
    }


    @Test
    @Order(2)
    public void testRegistrazioneEmailEsistente() throws IOException, ServletException, InvalidTextException, NicknameNotAvailableException, EmailNotAvailableException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);
        RegistrazioneService rs=mock(RegistrazioneService.class);
        String registrationErrata="./registerUser.jsp";

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("test@gmail.com");
        when(request.getParameter("nickname")).thenReturn("testt1");
        when(request.getParameter("password")).thenReturn("testtesttest");
        when(request.getRequestDispatcher("./registerUser.jsp")).thenReturn(requestDispatcher);


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new RegistrationVideogamerServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("Email"));
    }

    @Test
    @Order(3)
    public void testRegistrazioneNicknameEsistente() throws IOException, ServletException, InvalidTextException, NicknameNotAvailableException, EmailNotAvailableException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);
        RegistrazioneService rs=mock(RegistrazioneService.class);
        String registrationErrata="./registerUser.jsp";

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("test1@gmail.com");
        when(request.getParameter("nickname")).thenReturn("testt");
        when(request.getParameter("password")).thenReturn("testtesttest");
        when(request.getRequestDispatcher("./registerUser.jsp")).thenReturn(requestDispatcher);


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new RegistrationVideogamerServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("NicknameUsato"));
    }
}
