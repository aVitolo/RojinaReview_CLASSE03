package model.utilities;

import model.beans.*;
import model.dao.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class WrapperDaoAdminPanel {
    private static final String amministratoreClass = "Amministratore";
    private static final String giornalistaClass = "Giornalista";
    private static final String utenteClass = "Utente";
    private static final String notiziaClass = "Notizia";
    private static final String recensioneClass = "Recensione";
    private static final String prodottoClass = "Prodotto";

    public static ArrayList<Object> doRetriveAll(String className) throws SQLException, UnsupportedEncodingException {
        Object dao;
        Object lista;

        switch (className) {
            case amministratoreClass:
                dao = new AmministratoreDAO();
                lista = ((AmministratoreDAO) dao).doRetriveAll();
                break;
            case giornalistaClass:
                dao = new GiornalistaDAO();
                lista = ((GiornalistaDAO) dao).doRetriveAll();
                break;

            case utenteClass:
                dao = new UtenteDAO();
                lista = ((UtenteDAO) dao).doRetriveAll();
                break;

            case notiziaClass:
                dao = new NotiziaDAO();
                lista = ((NotiziaDAO) dao).doRetriveAll();
                break;

            case recensioneClass:
                dao = new RecensioneDAO();
                lista = ((RecensioneDAO) dao).doRetriveAll();
                break;

            case prodottoClass:
                dao = new ProdottoDAO();
                lista = ((ProdottoDAO) dao).doRetriveAll();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + className);
        }

        return (ArrayList<Object>) lista;
    }
}
