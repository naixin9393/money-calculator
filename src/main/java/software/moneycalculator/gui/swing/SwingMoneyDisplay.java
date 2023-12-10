package software.moneycalculator.gui.swing;

import software.moneycalculator.gui.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(double amount) {
        this.setText(String.valueOf(amount));
    }
}
