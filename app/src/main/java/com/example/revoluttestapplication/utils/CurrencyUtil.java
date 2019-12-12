package com.example.revoluttestapplication.utils;

import java.util.Currency;
import java.util.Locale;

/**
 * Currency is dedicated to manage the operations and utilities of currencies
 *
 * @author moeidheidari
 * @version 1.0
 */

public class CurrencyUtil
{

    /**
     * this method gets a string as currency code and returns the country code related to this currency symbol (Local)
     *
     * @param CurrencyCode
     * @return Locale
     */
    public static Locale getCountryCodeFromCurrencyCode(String CurrencyCode) {
        for (Locale item : Locale.getAvailableLocales()) {
            try {
                if (Currency.getInstance(item).getCurrencyCode().equalsIgnoreCase(CurrencyCode)) {
                    return item;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return Locale.UK;
    }
}
