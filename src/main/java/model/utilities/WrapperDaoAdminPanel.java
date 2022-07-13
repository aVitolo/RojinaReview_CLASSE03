package model.utilities;

import model.beans.*;
import model.dao.*;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
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
                ((AmministratoreDAO) dao).getCon().close();
                break;
            case giornalistaClass:
                dao = new GiornalistaDAO();
                lista = ((GiornalistaDAO) dao).doRetriveAll();
                ((GiornalistaDAO) dao).getCon().close();
                break;

            case utenteClass:
                dao = new UtenteDAO();
                lista = ((UtenteDAO) dao).doRetriveAll();
                ((UtenteDAO) dao).getCon().close();
                break;

            case notiziaClass:
                dao = new NotiziaDAO();
                lista = ((NotiziaDAO) dao).doRetriveAll();
                ((NotiziaDAO) dao).getCon().close();
                break;

            case recensioneClass:
                dao = new RecensioneDAO();
                lista = ((RecensioneDAO) dao).doRetriveAll();
                ((RecensioneDAO) dao).getCon().close();
                break;

            case prodottoClass:
                dao = new ProdottoDAO();
                lista = ((ProdottoDAO) dao).doRetriveAll();
                ((ProdottoDAO) dao).getCon().close();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + className);
        }
        return (ArrayList<Object>) lista;
    }

    public static void doRemoveEntry(String className, String identifier) throws SQLException {
        Object dao;

        switch (className) {
            case amministratoreClass:
                dao = new AmministratoreDAO();
                ((AmministratoreDAO) dao).doRemoveById(Integer.parseInt(identifier));
                ((AmministratoreDAO) dao).getCon().close();
                break;
            case giornalistaClass:
                dao = new GiornalistaDAO();
                ((GiornalistaDAO) dao).doRemoveById(Integer.parseInt(identifier));
                ((GiornalistaDAO) dao).getCon().close();
                break;

            case utenteClass:
                dao = new UtenteDAO();
                ((UtenteDAO) dao).doRemoveByEmail(identifier);
                ((UtenteDAO) dao).getCon().close();
                break;

            case notiziaClass:
                dao = new NotiziaDAO();
                ((NotiziaDAO) dao).doRemoveById(Integer.parseInt(identifier));
                ((NotiziaDAO) dao).getCon().close();
                break;

            case recensioneClass:
                dao = new RecensioneDAO();
                ((RecensioneDAO) dao).doRemoveById(Integer.parseInt(identifier));
                ((RecensioneDAO) dao).getCon().close();
                break;

            case prodottoClass:
                dao = new ProdottoDAO();
                ((ProdottoDAO) dao).doRemoveById(Integer.parseInt(identifier));
                ((ProdottoDAO) dao).getCon().close();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + className);
        }
    }
}
