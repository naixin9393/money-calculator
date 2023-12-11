package software.moneycalculator.gui;

import software.moneycalculator.Currency;
import software.moneycalculator.Money;

import java.util.List;

public interface FromMoneyPanel {
    Money get();
    FromMoneyPanel define(List<Currency> currencies);
    void setCurrency(Currency currency);
}
