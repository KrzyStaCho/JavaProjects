package Source;

public class CollatzConjecture {

    public static void run(String args) {
        CollatzConjecture.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niewłaściwa ilość argumentów");
            return;
        }

        int number;

        try {
            number = Integer.parseInt(args[0]);

            if(number<1) {
                System.out.println("Liczba musi być dodatnia");
                return;
            }

        } catch(NumberFormatException ex) {
            System.out.println("Niewłaściwa wartość argumentu");
            return;
        }

        int counter = 1;

        while(true) {

            System.out.print(number + " ");

            if(counter%5==0) {
                System.out.println();
            }

            if(number==1) {
                System.out.println();
                System.out.println("Ciąg wszedł w pętlę");
                System.out.println("Ilość cyfr przed petlą to: " + counter);
                break;
            }

            if(number % 2 == 0) {
                number /= 2;
            } else {
                number = (3*number) + 1;
            }

            counter++;
        }
    }
}
