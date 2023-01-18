import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.opinioneDAO.CommentoDAO;
import rojinaReview.opinione.controller.addCommentServlet;
import rojinaReview.registrazione.service.RegistrazioneService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InserimentoCommentoTest {
    @AfterAll
    static void clearResults() throws SQLException {
        CommentoDAO cDAO=new CommentoDAO();
        cDAO.deleteByText("test");
    }

    @Test
    public void inserisciCommentoCorretto() throws IOException, ServletException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);
        RegistrazioneService rs=mock(RegistrazioneService.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);



        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("videogiocatore")).thenReturn(videogiocatore);
        when(videogiocatore.getId()).thenReturn(1);
        when(request.getParameter("commentText")).thenReturn("test");
        when(request.getParameter("idContenuto")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("1");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new addCommentServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("commentoOK"));
    }

    @Test
    public void inserisciCommentoVuoto() throws IOException, ServletException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);
        RegistrazioneService rs=mock(RegistrazioneService.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);



        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession().getAttribute("videogiocatore")).thenReturn(videogiocatore);
        when(videogiocatore.getId()).thenReturn(1);
        when(request.getParameter("commentText")).thenReturn("");
        when(request.getParameter("idContenuto")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("1");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new addCommentServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("commEmpty"));
    }

    @Test
    public void inserisciCommentoLungo() throws IOException, ServletException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);
        RegistrazioneService rs=mock(RegistrazioneService.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);



        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession().getAttribute("videogiocatore")).thenReturn(videogiocatore);
        when(videogiocatore.getId()).thenReturn(1);
        when(request.getParameter("commentText")).thenReturn("fwivzNZK4hNrZYQHtnwdCQxt1jjBPJ3t5WFqiqMOHJlOWYXfBLOlzCEp0hO1WhSe9gSRzZArOi0hcTuNaZW1iCRPKTnhyI8KJyvpwgupoecsAWlFg793zhIDbezqowY5dBYKzw1YbDFYgnBBFMYlnhHrcWSTEH2xZ6Slt3SLJMCCZb58TwVtFsMwkWwIlRCKrNDLgsLFcjgj0JsKwGDdA2cAIN57DnjtqKHY8kg9rVnbptZhW48FDJgr6bvEEZsHl\n");
        when(request.getParameter("idContenuto")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("1");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new addCommentServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("commLong"));
    }
}
