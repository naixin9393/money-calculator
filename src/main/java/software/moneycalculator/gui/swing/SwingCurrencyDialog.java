package software.moneycalculator.gui.swing;

import software.moneycalculator.Currency;
import software.moneycalculator.gui.CurrencyDialog;

import javax.swing.*;
import java.util.List;

public class SwingCurrencyDialog extends JComboBox<Currency> implements CurrencyDialog {
    @Override
    public Currency get() {
        return this.getItemAt(this.getSelectedIndex());
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        for (Currency currency : currencies) {
            this.addItem(currency);
        }
        return this;
    }
}
