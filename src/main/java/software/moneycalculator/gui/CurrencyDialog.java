package software.moneycalculator.gui;

import software.moneycalculator.Currency;

import java.util.List;

public interface CurrencyDialog {
    Currency get();
    CurrencyDialog define(List<Currency> currencies);
    void set(Currency currency);
}
