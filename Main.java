import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var currency = getUserCurrency();
        if (currency == null) {
            System.out.println("Selected invalid currency!");
            System.exit(2);
        }

        System.out.println("Selected currency: " + currency);
    }
    
    private static Currency getUserCurrency() {
        var scanner = new Scanner(System.in);

        System.out.println("Available currencies:\n1: PLN\n2: USD\n3: EUR\nChoose currency (number or full name):");
    
        var userInput = scanner.nextLine();
        scanner.close();

        Currency currency = null;
        try {
            currency = Currency.valueOf(userInput);
        } catch (IllegalArgumentException illegalArgumentException) {
            try {
                currency = Currency.values()[Integer.parseInt(userInput)];
            } catch (NumberFormatException numberFormatException) {
                return null;
            }
        }

        return currency;
    }
}
