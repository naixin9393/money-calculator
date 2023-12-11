package software.moneycalculator.gui.swing;

import software.moneycalculator.gui.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    public SwingMoneyDisplay() {
        this.setSize(6, 1);
        this.setText("0.00");
    }

    @Override
    public void show(double amount) {
        this.setText(String.format("%.2f", amount));
    }
}
