package Source;

import java.io.*;
import java.net.URL;

public class ImageDownloader {

    public static final String[] TypeOfExtension = {".jpg"};

    public static void run(String args) {
        ImageDownloader.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=2) {
            System.out.println("Niewłaściwa ilość argumentów");
            return;
        }

        String imageURL = args[0];
        String imagePathFile = args[1];

        try {
            CheckExtension(imageURL, imagePathFile);
            byte[] response = GetImageFromURL(imageURL);
            SaveImage(imagePathFile, response);
        } catch(ExtensionException e) {
            System.out.println(e.getMessage());
            return;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Plik został pobrany");
    }

    public static void CheckExtension(String imageURL, String imagePathFile) throws ExtensionException {

        boolean isGood = false;
        for(String extension : TypeOfExtension) {
            if(imageURL.endsWith(extension)) {
                isGood = true;
            }
        }

        if(!isGood) {
            throw new WrongExtension("Niewłaściwy rodzaj rozszerzenia");
        }

        String urlExtension = imageURL.substring(imageURL.length()-4);
        String pathExtension = imagePathFile.substring(imagePathFile.length()-4);
        if(!urlExtension.equals(pathExtension)) {
            throw new DifferentExtension("Różne rozszerzenia");
        }
    }

    public static byte[] GetImageFromURL(String imageURL) throws IOException {
        URL url = new URL(imageURL);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }

    public static void SaveImage(String imagePathFile, byte[] response) throws IOException {
        FileOutputStream fos = new FileOutputStream(imagePathFile);
        fos.write(response);
        fos.close();
    }
}

class ExtensionException extends  Exception {
    public ExtensionException(String message) {
        super(message);
    }
}

class DifferentExtension extends ExtensionException {
    public DifferentExtension(String message) {
        super(message);
    }
}

class WrongExtension extends ExtensionException {
    public WrongExtension(String message) {
        super(message);
    }
}