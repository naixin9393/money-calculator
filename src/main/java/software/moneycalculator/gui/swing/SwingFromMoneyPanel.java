package software.moneycalculator.gui.swing;

import software.moneycalculator.Currency;
import software.moneycalculator.Money;
import software.moneycalculator.gui.CurrencyComboBox;
import software.moneycalculator.gui.FromMoneyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingFromMoneyPanel extends JPanel implements FromMoneyPanel {
    private JTextField amountField;

    public SwingFromMoneyPanel() {
        this.setLayout(new FlowLayout());
    }

    private CurrencyComboBox currencyComboBox;

    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), currencyComboBox.get());
    }

    private double toDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public FromMoneyPanel define(List<Currency> currencies) {
        add(createAmountField());
        add(createCurrencyComboBox(currencies));
        return this;
    }

    @Override
    public void setCurrency(Currency currency) { currencyComboBox.set(currency);
    }

    private Component createCurrencyComboBox(List<Currency> currencies) {
        SwingCurrencyComboBox comboBox = new SwingCurrencyComboBox();
        comboBox.define(currencies);
        this.currencyComboBox = comboBox;
        return comboBox;
    }

    private Component createAmountField() {
        JTextField textField = new JTextField("0.00");
        textField.setColumns(5);
        this.amountField = textField;
        return textField;
    }
}
