package Source;

public class FizzBuzz {

    public static void run(String args) {
        FizzBuzz.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niedozwolona ilość argumentów");
            return;
        }

        int MaxValue;

        try {
            MaxValue = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            System.out.println("Nieprawidłowa wartość argumentu");
            return;
        }

        if(MaxValue<=1) {
            System.out.println("Wartość nie może być mniejsza od 1");
            return;
        }

        for(int i=1; i<=MaxValue; i++) {
            if(i%5==0 && i%3==0) {
                System.out.println("FizzBuzz");
            } else if(i%3==0) {
                System.out.println("Fizz");
            } else if(i%5==0) {
                System.out.println("Buzz");
            } else System.out.println(i);
        }
    }
}
