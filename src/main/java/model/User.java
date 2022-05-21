package model;

import java.util.ArrayList;

public class User {

    private String email;
    private String nickname;
    private String nome;
    private String cognome;
    // private Strin passrword; // Da valutare
    private int eta;
    private ArrayList<String> address;
    private ArrayList<String> cellNumbers;
    private ArrayList<PaymentMethod> metodiPagamento;
    private Carrello carello;
    private ArrayList<Ordine> ordini;

    /* Costructor */

    public User(){}

    /* Getter & Setter */

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public int getEtà() { return eta; }

    public void setEtà(int eta) { this.eta = eta; }

    public ArrayList<String> getAddress() { return address; }

    public void setAddress(ArrayList<String> address) { this.address = address; }

    public ArrayList<String> getCellNumbers() { return cellNumbers; }

    public void setCellNumbers(ArrayList<String> cellNumbers) { this.cellNumbers = cellNumbers; }

    public Carrello getCarello() { return carello; }

    public void setCarello(Carrello carello) { this.carello = carello; }

    public ArrayList<Ordine> getOrdini() { return ordini; }

    public void setOrdini(ArrayList<Ordine> ordini) { this.ordini = ordini; }

    public int getEta() {return eta;}

    public void setEta(int eta) {this.eta = eta;}

    public ArrayList<PaymentMethod> getMetodiPagamento() { return metodiPagamento;}

    public void setMetodiPagamento(ArrayList<PaymentMethod> metodiPagamento) { this.metodiPagamento = metodiPagamento;}

}
