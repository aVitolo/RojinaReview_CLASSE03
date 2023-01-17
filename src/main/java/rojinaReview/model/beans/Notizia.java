package rojinaReview.model.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Notizia extends Articolo {

    /* Attributes */

    private HashMap<Integer,String> giochi;
    /* Constructor */
    public Notizia(){
        super();
        giochi = new HashMap<>();
    }

    public Notizia(int id, String nome, String testo, String immagine, ArrayList<Commento> commenti, ArrayList<Paragrafo> paragrafi, Date dataScrittura, HashMap<Integer, String> giochi) {
        super(id, nome, testo, immagine, commenti, paragrafi, dataScrittura);
        this.giochi = giochi;
    }
    /* Getter and Setter */

    public HashMap<Integer,String> getGiochi() {
        return giochi;
    }

    public void setGiochi(HashMap<Integer,String> giochi) {
        this.giochi = giochi;
    }

    public String getTitoliMenzionati()
    {
        StringBuilder titoli = new StringBuilder();
        for(Map.Entry<Integer, String> set : giochi.entrySet())
        {
            titoli.append(set.getValue());
            titoli.append("  ");
        }

        return titoli.toString();
    }
}
