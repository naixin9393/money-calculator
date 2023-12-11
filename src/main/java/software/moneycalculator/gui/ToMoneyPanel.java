package software.moneycalculator.gui;

import software.moneycalculator.Currency;

import java.util.List;

public interface ToMoneyPanel {
    ToMoneyPanel define(List<Currency> currencies);
    Currency getCurrency();
    void setCurrency(Currency currency);
    void show(double amount);
}
