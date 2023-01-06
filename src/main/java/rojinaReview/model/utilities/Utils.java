package rojinaReview.model.utilities;

import jakarta.servlet.http.Part;

import java.io.*;

public class Utils {

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

        return "." + File.separator + "images" + File.separator + imageType + File.separator + fileName;
    }

    public static void saveImageFileSystem(String imageType, String fileName, Part filePart) throws IOException {
        String home = System.getProperty("user.home");
        String project = File.separator + "IdeaProjects" + File.separator + "RojinaReview" + File.separator + "src" + File.separator + "main" + File.separator + "webapp"  + File.separator + "static"+ File.separator + "images";
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
}

