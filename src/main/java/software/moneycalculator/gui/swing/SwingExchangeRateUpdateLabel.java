package software.moneycalculator.gui.swing;

import software.moneycalculator.ExchangeRate;
import software.moneycalculator.gui.ExchangeRateUpdateLabel;

import javax.swing.*;

public class SwingExchangeRateUpdateLabel extends JLabel implements ExchangeRateUpdateLabel {
    public SwingExchangeRateUpdateLabel() {
        this.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

    @Override
    public void show(ExchangeRate exchangeRate) {
        this.setText("Exchange rate last updated: " + exchangeRate.date());
    }
}
