package rojinaReview.model.utilities;

import jakarta.servlet.http.Part;
import jakarta.xml.bind.DatatypeConverter;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.exception.InvalidTextException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    //Funzione che verifica se l' input è valido
    private static void textCheck(String text, int MinLenght, int MaxLenght, String[] badCharacters) throws InvalidTextException {
        if (text.length() >= MinLenght && text.length() <= MaxLenght) {
            for (int i = 0; i < badCharacters.length; i++) {
                if (text.contains(badCharacters[i])) {
                    throw new InvalidTextException();
                }
            }
            return;
        }
        throw new InvalidTextException();
    }

    public static void textCheckPassword(String password) throws InvalidTextException {
        //Info costanti per validazione input
        final String[] badCharacters = {"#", "-", " ", "\'", "\""};
        final int passwordMinLenght = 6;
        final int passwordMaxLenght = 20;

        textCheck(password, passwordMinLenght, passwordMaxLenght, badCharacters);
    }

    public static void textCheckEmail(String email) throws InvalidTextException {
        //Info costanti per validazione input
        final String[] badCharacters = {"#", "-", " ", "\'", "\""};
        final int emailMinLenght = 5;
        final int emailMaxLenght = 30;

        textCheck(email, emailMinLenght, emailMaxLenght, badCharacters);
    }

    public  static void textCheckNickname(String nickname) throws InvalidTextException {
        final String[] badCharacters = {"#", "-", " ", "\'", "\""};
        final int nickNameMinLenght = 5;
        final int nickNameMaxLenght = 30;

        textCheck(nickname, nickNameMinLenght, nickNameMaxLenght, badCharacters);
    }

    /* Password Hashing */

    public static String calcolaHash(String password) throws UnsupportedEncodingException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encoded = DatatypeConverter.printHexBinary(hash);
            return new String(encoded.toLowerCase());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Carrello mergeCarts(Carrello carrello, Carrello ospite) {
        if(ospite!=null) {
            int i, j;
            boolean trovato;
            ArrayList<Prodotto> prodottiDB = carrello.getProdotti();
            ArrayList<Prodotto> prodottiOspite = ospite.getProdotti();
            for (i = 0; i < prodottiOspite.size(); i++) {
                for (j = 0, trovato = false; j < prodottiDB.size() && !trovato; j++) {
                    if (prodottiOspite.get(i).getId() == prodottiDB.get(j).getId()) {
                        prodottiDB.get(j).setQuantità(prodottiDB.get(j).getQuantità() + prodottiOspite.get(i).getQuantità());
                        trovato = true;
                    }
                }
                //prodotto del carrello ospite non trovato nel DB
                if (!trovato)
                    prodottiDB.add(prodottiOspite.get(i));
            }
            carrello.setProdotti(prodottiDB); //istruzione penso inutile
            carrello.setTotale(carrello.getTotale() + ospite.getTotale());
        }
        return carrello;
    }

    //funzione che salva un file nella directory del project e restituisce la path
    public static String saveImageWar(String imageType, String fileName, Part filePart) throws IOException {
        String partialPath = ".." + File.separator + "webapps" + File.separator + "Rojina_Review_war" +  File.separator + "static" + File.separator + "images";
        String path = partialPath + File.separator + imageType;

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
            if (filecontent != null)
                filecontent.close();

        }

        return "." + File.separator + "static" + File.separator + "images" + File.separator + imageType + File.separator + fileName;
    }

    public static void saveImageFileSystem(String imageType, String fileName, Part filePart) throws IOException {
        String home = System.getProperty("user.home");
        String project = File.separator + "IdeaProjects" + File.separator + "RojinaReview_CLASSE03" + File.separator + "src" + File.separator + "main" + File.separator + "webapp"  + File.separator + "static"+ File.separator + "images";
        String path = home + project + File.separator + imageType;

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
            if (filecontent != null)
                filecontent.close();

        }
    }

    //funzione usata nell'inserimento notizia per parsare i giochi menzionati
    public static HashMap<Integer, String> parseGames(String toParse, ArrayList<String> allGames) throws SQLException {
        VideogiocoDAO vDAO = new VideogiocoDAO();
        HashMap<Integer, String> parsedGames = new HashMap<>();

        for (String g : allGames)
            if(toParse.contains(g)) {
                try {
                    parsedGames.put(vDAO.retrieveIdByName(g), g);
                } catch (SQLException e) {
                    e.printStackTrace();
                    continue;
                }
            }


        return parsedGames;
    }
}

