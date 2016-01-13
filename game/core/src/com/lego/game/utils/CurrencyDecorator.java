package com.lego.game.utils;

/**
 * Created by sargis on 9/18/15.
 */
public final class CurrencyDecorator {
    public static String decorate(String currency) {
        String decoratedCurrency = currency;
        switch (currency) {
            case "USD":
                decoratedCurrency = "$";
                break;
        }
        return decoratedCurrency;
    }
}
