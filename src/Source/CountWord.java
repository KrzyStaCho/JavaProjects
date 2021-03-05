package Source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CountWord {

    public static void run(String args) {
        CountWord.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niedozwolona ilość argumentów w pliku");
            return;
        }

        ArrayList<String> list = new ArrayList<>();

        try {
            list = GetContentInFile(args[0]);
        } catch(FileNotFoundException e) {
            System.out.println("Nieprawidłowa ścieżka do pliku");
        }

        int result = list.parallelStream().map(CountWord::GetCountOfWordsInLine).reduce(Integer::sum).get();

        System.out.println("Liczba słów w pliku to: " + result);
        System.out.println("Liczba linii w pliku to: " + list.size());
    }

    public static ArrayList<String> GetContentInFile(String pathFile) throws FileNotFoundException {

        File file = new File(pathFile);
        Scanner fileReader = new Scanner(file);

        ArrayList<String> list = new ArrayList<>();
        while(fileReader.hasNextLine()) {
            list.add(fileReader.nextLine());
        }

        return list;
    }

    public static int GetCountOfWordsInLine(String line) {
        Helper helper = Helper.SPACE;
        int counter = 0;

        char[] charTab = line.toCharArray();
        for(int i=0; i<line.length(); i++) {
            if(helper==Helper.SPACE && charTab[i]!=' ') {
                ++counter;
                helper = Helper.LETTER;
            } else if(helper==Helper.LETTER && charTab[i]==' ') {
                helper = Helper.SPACE;
            }
        }
        return counter;
    }
}

enum Helper {
    SPACE, LETTER
}