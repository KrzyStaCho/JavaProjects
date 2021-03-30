package Source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CountVowels {

    public static final char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

    public static void run(String args) {
        CountVowels.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niewłaściwa ilość argumentów");
            return;
        }

        String pathFile = args[0];
        ArrayList<String> content;

        try {
            content = new FileReader(pathFile).Read();
        } catch(FileNotFoundException e) {
            System.out.println("Plik nie został odnaleziony");
            return;
        }

        int countOfVowels = content.parallelStream().map(CountVowels::CountOfVowels).reduce(Integer::sum).get();

        System.out.println(countOfVowels);
    }

    public static int CountOfVowels(String line) {

        char[] tab = line.toLowerCase().toCharArray();

        int counter = 0;
        for(char character : tab) {
            for(char vowel : vowels) {
                if(character==vowel) {
                    counter++;
                    break;
                }
            }
        }

        return counter;
    }
}

class FileReader {

    private String pathFile;

    public FileReader(String pathFile) {
        this.pathFile = pathFile;
    }

    public ArrayList<String> Read() throws FileNotFoundException {

        ArrayList<String> content = new ArrayList<>();
        File file = new File(pathFile);
        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {
            content.add(fileReader.nextLine());
        }

        return content;
    }
}