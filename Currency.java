import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Currency {
    PLN(4.02),
    USD(1),
    EUR(0.92);

    private final double exchangeRate;

    private Currency(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public static double convert(Currency currencyFrom, Currency currencyTo, double amount) {
        var convertedAmount = amount / currencyFrom.exchangeRate * currencyTo.exchangeRate;
        return new BigDecimal(convertedAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static Currency getFromString(String currency) {
        try {
            return Currency.valueOf(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
