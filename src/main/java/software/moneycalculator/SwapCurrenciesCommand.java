package software.moneycalculator;

import software.moneycalculator.gui.CurrencyDialog;
import software.moneycalculator.gui.MoneyDialog;

public class SwapCurrenciesCommand implements Command{
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;

    public SwapCurrenciesCommand(MoneyDialog moneyDialog, CurrencyDialog toCurrencyDialog) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = toCurrencyDialog;
    }

    @Override
    public void execute() {
        Currency toCurrency = currencyDialog.get();
        currencyDialog.set(moneyDialog.get().currency());
        moneyDialog.setCurrency(toCurrency);
    }
}
