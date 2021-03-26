package Source;

public class LoveCalculator {

    public static void run(String args) {
        LoveCalculator.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=2) {
            System.out.println("Niewlasciwa ilosc argumentow");
        }

        char[] firstName = args[0].toCharArray();
        char[] secondName = args[1].toCharArray();

        int percentage = CalculatePercentage(firstName, secondName);

        System.out.println("Poziom zgodności to " + percentage + "%");
    }

    public static int CalculatePercentage(char[] firstName, char[] secondName) {

        double firstNameValue = 0;
        double secondNameValue = 0;

        for(char character : firstName) {
            firstNameValue += (int) character;
        }

        for(char character : secondName) {
            secondNameValue += (int) character;
        }

        if(firstNameValue<secondNameValue) {
            return (int) ((firstNameValue/secondNameValue) * 100);
        } else {
            return (int) ((secondNameValue/firstNameValue) * 100);
        }
    }
}
