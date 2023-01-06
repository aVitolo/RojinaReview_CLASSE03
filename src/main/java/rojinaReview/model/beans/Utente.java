package rojinaReview.model.beans;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String immagine;

    public Utente() {
    }

    public Utente(int id, String nome, String cognome, String email, String password, String immagine) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.immagine = immagine;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { //returns password hashed
        return password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) throws UnsupportedEncodingException {
        this.password = Utente.calcolaHash(password);
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /* Password Hashing */

    public static String calcolaHash(String password) throws UnsupportedEncodingException {
        StringBuilder passwordAfterHash = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (int i: hash) {
                hexString.append(Integer.toHexString(0XFF & i));
            }
            return new String(passwordAfterHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
