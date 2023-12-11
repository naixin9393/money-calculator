package software.moneycalculator.gui.swing;

import software.moneycalculator.Currency;
import software.moneycalculator.Money;
import software.moneycalculator.gui.CurrencyDialog;
import software.moneycalculator.gui.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;

    public SwingMoneyDialog() {
        this.setLayout(new FlowLayout());
    }

    private CurrencyDialog currencyDialog;

    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), currencyDialog.get());
    }

    private double toDouble(String text) {
        return Double.parseDouble(text);
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createAmountField());
        add(createCurrencyDialog(currencies));
        return this;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currencyDialog.set(currency);
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createAmountField() {
        JTextField textField = new JTextField();
        textField.setColumns(6);
        textField.setText("0.00");
        this.amountField = textField;
        return textField;
    }
}
