package Source;

import java.util.Random;

public class PasswordGenerator {

    public static void run(String args) {
        PasswordGenerator.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niewłaściwa ilość argumentów");
            return;
        }

        int passwordLength;

        try {
            passwordLength = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            System.out.println("Niewłaściwa wartość argumentu");
            return;
        }

        System.out.println("Twoje Hasło to: " + PassGenerator.GeneratePassword(passwordLength));
    }
}

abstract class PassGenerator {
    private static final int[] BoundOfLowerCase = {97, 122};
    private static final int[] BoundOfUpperCase = {65, 90};
    private static final int[] BoundOfNumber = {48, 57};
    private static final String SpecialCharacters = "!@.";

    public static String GeneratePassword(int passwordLength) {

        String password = "";
        Random randomNumber = new Random();

        for(int i=0; i<passwordLength; i++) {
            switch (randomNumber.nextInt(4)) {
                case 0:
                    password += RandomChar(BoundOfLowerCase);
                    break;
                case 1:
                    password += RandomChar(BoundOfUpperCase);
                    break;
                case 2:
                    password += RandomChar(BoundOfNumber);
                    break;
                case 3:
                    password += RandomChar(SpecialCharacters);
                    break;
            }
        }
        return password;
    }

    private static char RandomChar(int[] boundOfCharacters) {
        int range = boundOfCharacters[1] - boundOfCharacters[0] + 1;
        return (char) (new Random().nextInt(range)+boundOfCharacters[0]);
    }

    private static char RandomChar(String listOfCharacters) {
        int range = listOfCharacters.length();
        return listOfCharacters.toCharArray()[new Random().nextInt(range)];
    }
}
