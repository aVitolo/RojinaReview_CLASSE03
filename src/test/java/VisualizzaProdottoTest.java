import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Parere;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.dao.ProdottoDAO;
import rojinaReview.shop.service.ShopServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class VisualizzaProdottoTest {
    private ShopServiceImpl shopService;
    ProdottoDAO pDAO=new ProdottoDAO();

    public VisualizzaProdottoTest() throws SQLException {
    }

    @Test
    public void visualizzaProdottoIdCorretto(){
        Prodotto expected=new Prodotto(1,"Tazza Dark Souls","Simpatica tazza ispirata a...","./static/images/products/Tazza Dark Souls.jpg",new ArrayList<Commento>(),"Casa",new ArrayList<Parere>(),30,0, (float) 0, 15.00 F);
        Prodotto actual=null;
        try {
            actual=pDAO.doRetrieveById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(expected,actual);
    }
}
