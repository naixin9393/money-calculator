package software.moneycalculator.gui.commands;

import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeRate;
import software.moneycalculator.Money;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.ExchangeRateUpdateLabel;
import software.moneycalculator.gui.FromMoneyPanel;
import software.moneycalculator.gui.ToMoneyPanel;

import javax.swing.*;
import java.io.IOException;

public class ExchangeMoneyCommand implements Command {
    private final FromMoneyPanel fromMoneyPanel;
    private final ToMoneyPanel toMoneyPanel;
    private final ERAPIExchangeRateLoader exchangeRateLoader;
    private final ExchangeRateUpdateLabel exchangeRateUpdateLabel;

    public ExchangeMoneyCommand(ERAPIExchangeRateLoader exchangeRateLoader, FromMoneyPanel fromMoneyPanel, ToMoneyPanel toMoneyPanel, ExchangeRateUpdateLabel exchangeRateUpdateLabel) {
        this.fromMoneyPanel = fromMoneyPanel;
        this.toMoneyPanel = toMoneyPanel;
        this.exchangeRateLoader = exchangeRateLoader;
        this.exchangeRateUpdateLabel = exchangeRateUpdateLabel;
    }

    @Override
    public void execute() {
        Money money = fromMoneyPanel.get();
        Currency currency = toMoneyPanel.getCurrency();
        try {
            ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
            toMoneyPanel.show(money.amount() * exchangeRate.amount());
            exchangeRateUpdateLabel.show(exchangeRate);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't load exchange rate, check your internet connection and API documentation.", "Connection error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
