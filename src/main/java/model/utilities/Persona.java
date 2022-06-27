package model.utilities;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Persona {

    private String nome;
    private String cognome;
    private String email;
    private String password;

    public Persona(){

    }

    public Persona(String nome, String cognome, String email, String password) throws UnsupportedEncodingException {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = Persona.calcolaHash(password);
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
        this.password = Persona.calcolaHash(password);
    }

    /* Password Hashing */

    public static String calcolaHash(String password) throws UnsupportedEncodingException {
        StringBuilder  passwordAfterHash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest("A".getBytes("UTF-8"));

            passwordAfterHash = new StringBuilder();
            for (int i: hash) {
                passwordAfterHash.append(Integer.toHexString(0XFF & i));
            }
            return passwordAfterHash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
