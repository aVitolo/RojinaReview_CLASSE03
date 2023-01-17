package rojinaReview.model.beans;

import java.util.ArrayList;

public class Videogiocatore extends Utente {

    private ArrayList<Indirizzo> indirizzi;
    private ArrayList<String> telefoni;
    private ArrayList<Pagamento> pagamenti;
    private ArrayList<Ordine> ordini;
    private ArrayList<Commento> commenti;
    private ArrayList<Segnalazione> segnalazioni;
    private ArrayList<Parere> pareri;
    private Carrello carrello;
    private String nickname;

    public Videogiocatore(){
        super();
        indirizzi = new ArrayList<>();
        telefoni = new ArrayList<>();
        pagamenti = new ArrayList<>();
        ordini = new ArrayList<>();
        commenti = new ArrayList<>();
        segnalazioni = new ArrayList<>();
        pareri = new ArrayList<>();
        carrello = new Carrello();
    };

    public Videogiocatore(int id, String nome, String cognome, String email, String password, String immagine, ArrayList<Indirizzo> indirizzi, ArrayList<String> telefoni, ArrayList<Pagamento> pagamenti, ArrayList<Ordine> ordini, ArrayList<Commento> commenti, ArrayList<Segnalazione> segnalazioni, ArrayList<Parere> pareri, Carrello carrello, boolean bannato, String nickname) {
        super(id, nome, cognome, email, password, immagine);
        this.indirizzi = indirizzi;
        this.telefoni = telefoni;
        this.pagamenti = pagamenti;
        this.ordini = ordini;
        this.commenti = commenti;
        this.segnalazioni = segnalazioni;
        this.pareri = pareri;
        this.carrello = carrello;
        this.nickname = nickname;
    }

    /*
            Costruttore Usato in fase di Registrazione
     */
    public Videogiocatore(String email, String password, String nickname) {
        super(0,null, null, email, password, null);
    }


    public ArrayList<Indirizzo> getIndirizzi() {
        return indirizzi;
    }

    public void setIndirizzi(ArrayList<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public ArrayList<String> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(ArrayList<String> telefoni) {
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

    public ArrayList<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        this.commenti = commenti;
    }

    public ArrayList<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public void setSegnalazioni(ArrayList<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }

    public ArrayList<Parere> getPareri() {
        return pareri;
    }

    public void setPareri(ArrayList<Parere> pareri) {
        this.pareri = pareri;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
