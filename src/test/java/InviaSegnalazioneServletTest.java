import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.opinione.controller.inviaSegnalazioneServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InviaSegnalazioneServletTest {
    @Test
    public void testSegnalazioneNoCommento() throws IOException, ServletException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);

        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession().getAttribute("videogiocatore")).thenReturn(videogiocatore);
        when(request.getParameter("id_commento")).thenReturn("1");
        when(request.getParameter("flag")).thenReturn("1");
        when(request.getParameter("commento_aggiuntivo")).thenReturn("");
        when(request.getParameter("motivo")).thenReturn("Altro");
        when(videogiocatore.getId()).thenReturn(1);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new inviaSegnalazioneServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("noComm"));
    }

    @Test
    public void testSegnalazioneNoMotivo() throws IOException, ServletException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);

        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession().getAttribute("videogiocatore")).thenReturn(videogiocatore);
        when(request.getParameter("id_commento")).thenReturn("1");
        when(request.getParameter("flag")).thenReturn("1");
        when(request.getParameter("commento_aggiuntivo")).thenReturn("");
        when(request.getParameter("motivo")).thenReturn("seleziona");
        when(videogiocatore.getId()).thenReturn(1);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new inviaSegnalazioneServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("noMot"));
    }

    @Test
    public void testSegnalazioneCorretta() throws IOException, ServletException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        HttpSession session=mock(HttpSession.class);
        Videogiocatore videogiocatore=mock(Videogiocatore.class);

        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession().getAttribute("videogiocatore")).thenReturn(videogiocatore);
        when(request.getParameter("id_commento")).thenReturn("1");
        when(request.getParameter("flag")).thenReturn("1");
        when(request.getParameter("commento_aggiuntivo")).thenReturn("");
        when(request.getParameter("motivo")).thenReturn("Spam");
        when(videogiocatore.getId()).thenReturn(1);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new inviaSegnalazioneServlet().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("segnalazioneOK"));
    }
}
