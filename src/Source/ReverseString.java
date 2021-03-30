package Source;

public class ReverseString {

    public static void run(String args) {
        ReverseString.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=1) {
            System.out.println("Niewłaściwa ilość argumentów");
        }

        String string = args[0];

        System.out.println(Reverse(string));
    }

    public static String Reverse(String string) {

        int length = string.length();
        String reverseString = "";

        for(int i=length-1; i>=0; i--) {
            reverseString += String.valueOf(string.charAt(i));
        }

        return reverseString;
    }
}
