package Source;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class EncryptorDescryptor {

    public static void run(String args) {
        EncryptorDescryptor.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=2) {
            System.out.println("Nieprawidłowa ilość argumentów");
            return;
        }

        String pathFile = args[0];
        boolean encoder;

        if(args[1].equals("Encode")) encoder = true;
        else if(args[1].equals("Decode")) encoder = false;
        else {
            System.out.println("Niewłaściwy tryb");
            return;
        }

        try {

            ArrayList<String> content = ReadWriteFile.GetContentInFile(pathFile);

            if(content.isEmpty()) {
                System.out.println("Plik jest pusty");
                return;
            }

            ArrayList<String> newContent = new ArrayList<>();

            if(encoder) {
                for(String line : content) {
                    byte[] byteLine = Base64.getEncoder().encode(line.getBytes());
                    newContent.add(new String(byteLine));
                }
            } else {
                for(String line : content) {
                    byte[] byteLine = Base64.getDecoder().decode(line.getBytes());
                    newContent.add(new String(byteLine));
                }
            }

            ReadWriteFile.WriteContentInFile(pathFile, newContent);

            System.out.println("Plik zakodowany");

        } catch(FileNotFoundException e) {
            System.out.println("Plik nie odnaleziony");
            return;
        } catch(Exception e) {
            System.out.println("Blad: ");
            e.printStackTrace();
        }
    }
}

class ReadWriteFile {

    public static ArrayList<String> GetContentInFile(String pathFile) throws FileNotFoundException {

        File file = new File(pathFile);
        Scanner fileReader = new Scanner(file);
        ArrayList<String> content = new ArrayList<>();

        while(fileReader.hasNextLine()) {
            content.add(fileReader.nextLine());
        }

        return content;
    }

    public static void WriteContentInFile(String pathFile, ArrayList<String> content) throws FileNotFoundException, IOException {

        FileWriter fileWriter = new FileWriter(pathFile, false);

        for(String line : content) {
            fileWriter.write(line);
            fileWriter.write("\n");
        }

        fileWriter.close();
    }
}
