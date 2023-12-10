package software.moneycalculator;

import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.CurrencyDialog;
import software.moneycalculator.gui.MoneyDialog;
import software.moneycalculator.gui.swing.SwingMoneyDisplay;

import java.io.IOException;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currncyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final ERAPIExchangeRateLoader exchangeRateLoader;

    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, SwingMoneyDisplay moneyDisplay, ERAPIExchangeRateLoader exchangeRateLoader) {
        this.moneyDialog = moneyDialog;
        this.currncyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    @Override
    public void execute() throws IOException {
        Money money = moneyDialog.get();
        Currency currency = currncyDialog.get();
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
        moneyDisplay.show(money.amount() * exchangeRate.amount());
    }
}