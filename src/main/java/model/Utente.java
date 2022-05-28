package model;

import java.util.ArrayList;

public class Utente {

    private int eta;
    private String email;
    private String nickname;
    private String nome;
    private String cognome;
    private String password;
    private ArrayList<String> indirizzi;
    private ArrayList<String> telefoni;
    private ArrayList<Pagemento> pagamenti;
    private ArrayList<Ordine> ordini;
    private Carrello carello;

    /* Costructor */

    public Utente(){}

    /* Getter & Setter */

    public int getEta() {return eta;}

    public void setEta(int eta) {this.eta = eta;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNickname() {return nickname;}

    public void setNickname(String nickname) {this.nickname = nickname;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public ArrayList<String> getIndirizzi() {return indirizzi;}

    public void setIndirizzi(ArrayList<String> indirizzi) {this.indirizzi = indirizzi;}

    public ArrayList<String> getTelefoni() {return telefoni;}

    public void setTelefoni(ArrayList<String> telefoni) {this.telefoni = telefoni;}

    public ArrayList<Pagemento> getPagamenti() {return pagamenti;}

    public void setPagamenti(ArrayList<Pagemento> pagamenti) {this.pagamenti = pagamenti;}

    public ArrayList<Ordine> getOrdini() {return ordini;}

    public void setOrdini(ArrayList<Ordine> ordini) {this.ordini = ordini;}

    public Carrello getCarello() {return carello;}

    public void setCarello(Carrello carello) {this.carello = carello;}
}
