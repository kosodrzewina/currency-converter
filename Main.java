import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
    
        System.out.println("Available currencies:\n1: PLN\n2: USD\n3: EUR\nSelect currency to convert from:");
    
        var currencyFrom = Currency.getFromString(scanner.nextLine().toUpperCase());
        if (currencyFrom == null) {
            System.out.println("Selected invalid currency!");
            System.exit(2);
        }

        System.out.println("Select currency to convert to:");

        var currencyTo = Currency.getFromString(scanner.nextLine().toUpperCase());
        if (currencyTo == null) {
            System.out.println("Selected invalid currency!");
            System.exit(2);
        }

        System.out.println("Enter the amount of " + currencyFrom + ":");

        Double amount = null;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
            System.exit(2);
        }

        if (new BigDecimal(amount).scale() > 2) {
            System.out.println("The amount can have maximum of two decimal digits!");
            System.exit(2);
        }

        scanner.close();

        var convertedAmount = Currency.convert(currencyFrom, currencyTo, amount);

        System.out.println(amount + " " + currencyFrom + " = " + convertedAmount + " " + currencyTo);
        System.exit(0);
    }
}
