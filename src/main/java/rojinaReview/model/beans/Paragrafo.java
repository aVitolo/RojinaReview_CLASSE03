package rojinaReview.model.beans;

public class Paragrafo{
    private String titolo;
    private String testo;
    private String immagine;

    public Paragrafo(String titolo, String testo, String immagine){
        this.titolo=titolo;
        this.testo=testo;
        this.immagine=immagine;
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