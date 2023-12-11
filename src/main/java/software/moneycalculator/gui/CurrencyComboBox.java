package software.moneycalculator.gui;

import software.moneycalculator.Currency;

import java.util.List;

public interface CurrencyComboBox {
    Currency get();
    void define(List<Currency> currencies);
    void set(Currency currency);
}
