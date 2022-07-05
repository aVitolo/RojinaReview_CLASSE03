package model.utilities;

import jakarta.servlet.http.Part;

import java.io.*;

public class Utils {

    //funzione che salva un file nella directory del project e restituisce la path
    public static String saveImageWar(String imageType, String fileName, Part filePart) throws IOException {
        String partialPath = "../webapps/Rojina_Review_war/images";
        String path = partialPath + File.separator + imageType;

        OutputStream out = null;
        InputStream filecontent = null;

        try{
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(out != null)
                out.close();
            if(filecontent != null)
                filecontent.close();

        }

        return "./images"+ File.separator + imageType + File.separator + fileName;
    }

    public static void saveImageFileSystem(String imageType, String fileName, Part filePart) throws IOException {
        String home = System.getProperty("user.home");
        String project = "/IdeaProjects/RojinaReview/src/main/webapp/images";
        String path = home + project + File.separator + imageType;

        OutputStream out = null;
        InputStream filecontent = null;

        try{
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(out != null)
                out.close();
            if(filecontent != null)
                filecontent.close();

        }
    }
}

