package Source.NameGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Application {

    public static int NAME = 5163;
    public static int SURNAME = 88799;

    public static void run() {
        Application.main(null);
    }

    public static void main(String[] args) {

        Random random = new Random();
        int randomForName = random.nextInt()%NAME+1;
        int randomForSurname = random.nextInt()%SURNAME+1;

        //TODO: Do zmiany
        File nameFile = new File("src/Source/NameGenerator/Name.txt");
        File surnameFile = new File("src/Source/NameGenerator/Surname.txt");

        String name = "";
        String surname = "";

        try {
            Scanner fileReader = new Scanner(nameFile);
            for(int i=0; i<randomForName; i++) {
                fileReader.nextLine();
            }
            name = fileReader.nextLine();

            fileReader = new Scanner(surnameFile);

            for(int i=0; i<randomForSurname; i++) {
                fileReader.nextLine();
            }
            surname = fileReader.nextLine();
        } catch(FileNotFoundException e) {
            System.out.println("Plik nie został odnaleziony");
        }

        System.out.println(name + " " + surname);
    }
}
