package software.moneycalculator.gui.commands;

import software.moneycalculator.Currency;
import software.moneycalculator.gui.FromMoneyPanel;
import software.moneycalculator.gui.ToMoneyPanel;

public class SwapCurrenciesCommand implements Command{
    private final FromMoneyPanel fromMoneyPanel;
    private final ToMoneyPanel toMoneyPanel;

    public SwapCurrenciesCommand(FromMoneyPanel fromMoneyPanel, ToMoneyPanel toMoneyPanel) {
        this.fromMoneyPanel = fromMoneyPanel;
        this.toMoneyPanel = toMoneyPanel;
    }

    @Override
    public void execute() {
        Currency toCurrency = toMoneyPanel.getCurrency();
        toMoneyPanel.setCurrency(fromMoneyPanel.get().currency());
        fromMoneyPanel.setCurrency(toCurrency);
    }
}
