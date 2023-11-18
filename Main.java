import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid amount of arguments!\n");
            System.out.println("Usage: currency-converter <currency_from> <currency_to> <amount>\n");

            System.out.println("currency_from: Currency to convert from.");
            System.out.println("currency_to: Currency to convert to.");
            System.out.println("amount: Amount to be converted (has to have maximum of 2 decimal digits).\n");

            System.out.println("Available currencies: PLN, USD, EUR.");

            System.exit(2);
        }

        var currencyFromArg = args[0];
        var currencyToArg = args[1];
        var amountArg = args[2];

        var parseResult = parseArgs(currencyFromArg, currencyToArg, amountArg);
        if (!parseResult.isSuccessfull()) {
            System.out.println(parseResult.getMessage());
            System.exit(2);
        }

        var currencyFrom = parseResult.getData().getCurrencyFrom();
        var currencyTo = parseResult.getData().getCurrencyTo();
        var amount = parseResult.getData().getAmount();

        var convertedAmount = Currency.convert(currencyFrom, currencyTo, amount);

        System.out.println(amount + " " + currencyFrom + " = " + convertedAmount + " " + currencyTo);
        System.exit(0);
    }

    public static ParseResult<Arguments> parseArgs(String currencyFromString, String currencyToString, String amountString) {
        var currencyFrom = Currency.getFromString(currencyFromString);
        if (currencyFrom == null) {
            return ParseResult.createFailure("'" + currencyFromString + "' is an invalid or unavailable currency!");
        }

        var currencyTo = Currency.getFromString(currencyToString);
        if (currencyTo == null) {
            return ParseResult.createFailure("'" + currencyToString + "' is an invalid or unavailable currency!");
        }

        Double amount = null;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            return ParseResult.createFailure("'" + amountString + "' is an invalid amount!");
        }

        if (new BigDecimal(amount).scale() > 2) {
            return ParseResult.createFailure("The amount can have maximum of two decimal digits!");
        }

        return ParseResult.createSuccess(new Arguments(currencyFrom, currencyTo, amount));
    }
}
