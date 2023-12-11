package software.moneycalculator;

import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.CurrencyDialog;
import software.moneycalculator.gui.MoneyDialog;
import software.moneycalculator.gui.swing.SwingMoneyDisplay;

import javax.swing.*;
import java.awt.*;
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
        ExchangeRate exchangeRate = null;
        try {
            exchangeRate = exchangeRateLoader.load(money.currency(), currency);
            moneyDisplay.show(money.amount() * exchangeRate.amount());
        } catch (IOException e) {
            JOptionPane.showMessageDialog((Component) moneyDialog, "Couldn't load exchange rate");
        }
    }
}
