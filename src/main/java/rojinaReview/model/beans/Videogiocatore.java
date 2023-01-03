package rojinaReview.model.beans;

import rojinaReview.model.utilities.Persona;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Videogiocatore extends Persona {
    static public String[] fieldsName = {"Email","Nickname","Password Hash","Nome","Cognome","Età"};

    private ArrayList<Indirizzo> indirizzi;
    private ArrayList<Telefono> telefoni;
    private ArrayList<Pagamento> pagamenti;
    private ArrayList<Ordine> ordini;
    private ArrayList<Commento> commenti;
    private ArrayList<Parere> voti;
    private Carrello carrello;
    private int eta;
    private boolean bannato;
    private String nickname;


    /* Costructor */

    public Videogiocatore() {
        super();
    }

    public Videogiocatore(String email, String password, String nickname) {
        super(email, password);
        this.nickname = nickname;
    }

    public Videogiocatore(String nome, String cognome, String email, String password, int eta, String nickname) throws UnsupportedEncodingException {
        super(nome, cognome, email, password);
        this.eta = eta;
        this.nickname = nickname;
    }

    /* Contructor for insert in DB */

    public Videogiocatore(int eta,
                          String email,
                          String nickname,
                          String nome,
                          String cognome,
                          String password,
                          ArrayList<Indirizzo> indirizzi,
                          ArrayList<Telefono> telefoni,
                          ArrayList<Pagamento> pagamenti,
                          ArrayList<Ordine> ordini,
                          Carrello carrello,
                          String immagine)
            throws UnsupportedEncodingException {
        super(nome, cognome, email, password, immagine);
        this.eta = eta;
        this.nickname = nickname;
        this.indirizzi = indirizzi;
        this.telefoni = telefoni;
        this.pagamenti = pagamenti;
        this.ordini = ordini;
        this.carrello = carrello;

    }

    /* Getter & Setter */

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<Indirizzo> getIndirizzi() {
        return indirizzi;
    }

    public void setIndirizzi(ArrayList<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public ArrayList<Telefono> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(ArrayList<Telefono> telefoni) {
        this.telefoni = telefoni;
    }

    public ArrayList<Pagamento> getPagamenti() {
        return pagamenti;
    }

    public void setPagamenti(ArrayList<Pagamento> pagamenti) {
        this.pagamenti = pagamenti;
    }

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(ArrayList<Ordine> ordini) {
        this.ordini = ordini;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public String getImmagine() {
        return super.getImmagine();
    }

    public void setImmagine(String immagine) {
        super.setImmagine(immagine);
    }
}
