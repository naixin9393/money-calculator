package software.moneycalculator.gui;

import software.moneycalculator.Currency;
import software.moneycalculator.Money;

import java.util.List;

public interface MoneyDialog {
    Money get();
    MoneyDialog define(List<Currency> currencies);
}
