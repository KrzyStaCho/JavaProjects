package Source;

public class BMICalculator {

    public static void run(String args) {
        BMICalculator.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=2) {
            System.out.println("Niewłaściwa ilość argumentów");
            return;
        }

        int weight;
        double height;

        try {
            weight = Integer.parseInt(args[0]);
            height = Double.parseDouble(args[1])/100;
        } catch(NumberFormatException e) {
            System.out.println("Nieprawidłowy argument(y)");
            return;
        }

        double BMI = Math.round(GetBMI(weight, height));
        String BMIValue;
        if(BMI<18.5) BMIValue = "niedowaga";
        else if(BMI<=24.9) BMIValue = "waga prawidłowa";
        else if(BMI<=29.9) BMIValue = "nadwaga";
        else BMIValue = "otyłość";

        System.out.println(BMI + ": " + BMIValue);
    }

    public static double GetBMI(double weight, double height) {
        return (double) ( weight / Math.pow(height, 2) );
    }
}
