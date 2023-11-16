public enum Currency {
    PLN,
    USD,
    EUR;

    public static Currency getFromString(String currencyString) {
        var currency = getFromStringAsText(currencyString);
        if (currency == null) {
            currency = getFromStringAsNumber(currencyString);
        }

        return currency;
    }

    private static Currency getFromStringAsText(String currency) {
        try {
            return Currency.valueOf(currency);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static Currency getFromStringAsNumber(String currency) {
        try {
            return Currency.values()[Integer.parseInt(currency)];
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
