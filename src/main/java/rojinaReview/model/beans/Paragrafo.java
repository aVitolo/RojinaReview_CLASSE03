package rojinaReview.model.beans;

public class Paragrafo{
    private int id;
    private String titolo;
    private String testo;
    private String immagine;

    public Paragrafo() {
    }

    public Paragrafo(int id, String titolo, String testo, String immagine){
        this.id = id;
        this.titolo=titolo;
        this.testo=testo;
        this.immagine=immagine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}