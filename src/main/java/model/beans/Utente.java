package model.beans;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Utente {

    private ArrayList<Indirizzo> indirizzi;
    private ArrayList<Telefono> telefoni;
    private ArrayList<Pagamento> pagamenti;
    private ArrayList<Ordine> ordini;
    private Carrello carrello;
    private int eta;
    private String email;
    private String nickname;
    private String nome;
    private String cognome;
    private String password;

    /* Costructor */

    public Utente() {
    }

    /* Contructor for insert in DB */

    public Utente(int eta,
                  String email,
                  String nickname,
                  String nome,
                  String cognome,
                  String password,
                  ArrayList<Indirizzo> indirizzi,
                  ArrayList<Telefono> telefoni,
                  ArrayList<Pagamento> pagamenti,
                  ArrayList<Ordine> ordini,
                  Carrello carello)
            throws UnsupportedEncodingException {
        this.eta = eta;
        this.email = email;
        this.nickname = nickname;
        this.nome = nome;
        this.cognome = cognome;
        this.password = Utente.calcolaHash(password);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    /* Password Hashing */

    public static String calcolaHash(String password) throws UnsupportedEncodingException {
        String passwordAfterHash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            passwordAfterHash = String.format("%040x", new BigInteger(1, digest.digest()));
            return passwordAfterHash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
