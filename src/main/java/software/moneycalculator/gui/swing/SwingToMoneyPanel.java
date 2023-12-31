package software.moneycalculator.gui.swing;

import software.moneycalculator.Currency;
import software.moneycalculator.gui.CurrencyComboBox;
import software.moneycalculator.gui.ToMoneyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingToMoneyPanel extends JPanel implements ToMoneyPanel {
    private JTextField amountField;
    private CurrencyComboBox currencyComboBox;

    public SwingToMoneyPanel() {
        setLayout(new FlowLayout());
    }

    @Override
    public ToMoneyPanel define(List<Currency> currencies) {
        add(createTextField());
        add(createCurrencyComboBox(currencies));
        return this;
    }

    private Component createCurrencyComboBox(List<Currency> currencies) {
        SwingCurrencyComboBox comboBox = new SwingCurrencyComboBox();
        comboBox.define(currencies);
        this.currencyComboBox = comboBox;
        return comboBox;
    }

    private Component createTextField() {
        JTextField textField = new JTextField("0.00");
        textField.setColumns(5);
        textField.setFocusable(false);
        this.amountField = textField;
        return textField;
    }

    @Override
    public Currency getCurrency() {
        return currencyComboBox.get();
    }

    @Override
    public void setCurrency(Currency currency) {
        currencyComboBox.set(currency);
    }

    public void show(double amount) {
        amountField.setText(String.format("%.2f", amount));
    }
}
