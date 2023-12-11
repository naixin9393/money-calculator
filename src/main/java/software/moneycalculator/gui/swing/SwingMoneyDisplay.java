package software.moneycalculator.gui.swing;

import software.moneycalculator.gui.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JTextField implements MoneyDisplay {
    public SwingMoneyDisplay() {
        setColumns(5);
        this.setText("0.00");
        this.setFocusable(false);
    }

    @Override
    public void show(double amount) {
        this.setText(String.format("%.2f", amount));
    }
}
