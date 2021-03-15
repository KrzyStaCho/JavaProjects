package Source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void run(String args) {
        Hangman.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niewłaściwa ilosc argumentow");
            return;
        }

        String pathToResourceFile = args[0];
        ArrayList<String> resource;

        try {
            resource = ReadFile.GetAllContent(pathToResourceFile);
        } catch(FileNotFoundException e) {
            System.out.println("Plik nie został znaleziony");
            return;
        }

        String target = GetRandomWord(resource).toUpperCase();
        String userWord = "";

        for(char character : target.toCharArray()) {
            if(character==' ') userWord += " ";
            else if(character=='-') userWord += "-";
            else userWord += "?";
        }

        userWord.toUpperCase();
        ShowGUI(userWord);

        char[] charTarget = target.toCharArray();
        char[] charUserWord = userWord.toCharArray();
        int counterMiss = 0;

        boolean status = true;
        while(status) {

            char userChar;
            do {
                userChar = GetCharacterFromUser();
                userChar = Character.toUpperCase(userChar);
            } while(userChar=='?');

            boolean isGoodInput = false;
            for(int i=0; i<charUserWord.length; i++) {
                if(charUserWord[i]=='?' && charTarget[i]==userChar) {
                    charUserWord[i] = userChar;
                    userWord = String.valueOf(charUserWord);
                    isGoodInput = true;
                }
            }

            if(!isGoodInput) {
                counterMiss++;
            }

            ShowGUI(userWord);

            if(userWord.equals(target)) {
                System.out.println("Wygrałes! Koniec Gry");
                status = false;
            } else if(counterMiss>=5) {
                System.out.println("Przegrałes! Koniec Gry");
                status = false;
            }
        }
    }

    public static String GetRandomWord(ArrayList<String> words) {
        Random randomNumber = new Random();
        return words.get(randomNumber.nextInt(words.size()));
    }

    public static void ShowGUI(String word) {
        System.out.println("-------------------------------");
        for(char character : word.toCharArray()) {
            if(character=='?') {
                System.out.print("___");
            } else {
                System.out.print(character);
            }
            System.out.print("  ");
        }
        System.out.println();
        System.out.println("-------------------------------");
    }

    public static char GetCharacterFromUser() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if(userInput.length()!=1) {
            return '?';
        } else {
            return userInput.toCharArray()[0];
        }
    }
}

class ReadFile {
    public static ArrayList<String> GetAllContent(String pathFile) throws FileNotFoundException {
        File file = new File(pathFile);
        Scanner fileReader = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();

        while(fileReader.hasNextLine()) {
            lines.add(fileReader.nextLine());
        }

        return lines;
    }
}
