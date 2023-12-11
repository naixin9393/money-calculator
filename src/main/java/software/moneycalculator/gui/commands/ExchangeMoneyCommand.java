package software.moneycalculator.gui.commands;

import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeRate;
import software.moneycalculator.Money;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.CurrencyDialog;
import software.moneycalculator.gui.MoneyDialog;
import software.moneycalculator.gui.swing.SwingMoneyDisplay;

import javax.swing.*;
import java.io.IOException;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final ERAPIExchangeRateLoader exchangeRateLoader;

    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, SwingMoneyDisplay moneyDisplay, ERAPIExchangeRateLoader exchangeRateLoader) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();
        try {
            ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
            moneyDisplay.show(money.amount() * exchangeRate.amount());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't load exchange rate, check your internet connection and API documentation.", "Connection error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
