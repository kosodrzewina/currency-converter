public enum Currency {
    PLN(4.02),
    USD(1),
    EUR(0.92);

    private final double exchangeRate;

    private Currency(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public static double convert(Currency currencyFrom, Currency currencyTo, double amount) {
        return amount / currencyFrom.exchangeRate * currencyTo.exchangeRate;
    }

    public static Currency getFromString(String currency) {
        try {
            return Currency.valueOf(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
