package software.moneycalculator.gui.swing;

import software.moneycalculator.Currency;
import software.moneycalculator.gui.CurrencyComboBox;

import javax.swing.*;
import java.util.List;

public class SwingCurrencyComboBox extends JComboBox<Currency> implements CurrencyComboBox {
    @Override
    public Currency get() {
        return this.getItemAt(this.getSelectedIndex());
    }

    @Override
    public CurrencyComboBox define(List<Currency> currencies) {
        for (Currency currency : currencies) {
            this.addItem(currency);
        }
        return this;
    }

    @Override
    public void set(Currency currency) {
        this.setSelectedItem(currency);
    }
}
