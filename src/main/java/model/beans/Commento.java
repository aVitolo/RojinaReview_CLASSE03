package model.beans;

import java.util.Date;

public class Commento {

    private String testo;
    private String user;
    private Date data;

    /* Constructor */

    public Commento() {};

    /* Getter & Setter */

    public String getTesto() { return testo; }

    public void setTesto(String testo) { this.testo = testo; }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public Date getData() { return data; }

    public void setData(Date data) { this.data = data; }
}
