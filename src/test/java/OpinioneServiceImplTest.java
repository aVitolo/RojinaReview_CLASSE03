import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.dao.CommentoDAO;
import rojinaReview.model.dao.SegnalazioneDAO;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpinioneServiceImplTest {
    @InjectMocks
    private OpinioneServiceImpl opinioneService;
    @Mock
    CommentoDAO cDAO;
    @Mock
    SegnalazioneDAO sDAO;


    /*Funzione per testare la visualizzazione dei commenti segnalati*/
    @Test
    public void visualizzaCommentiSegnalati() throws SQLException {
        Commento commento=new Commento();
        ArrayList<Commento> segnalazioni=new ArrayList<>();
        segnalazioni.add(commento);
        when(sDAO.doRetrieveReportedComments()).thenReturn(segnalazioni);
        assertEquals(segnalazioni,sDAO.doRetrieveReportedComments());
    }
}
