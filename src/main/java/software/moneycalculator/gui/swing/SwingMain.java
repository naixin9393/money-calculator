package software.moneycalculator.gui.swing;


import software.moneycalculator.Command;
import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeMoneyCommand;
import software.moneycalculator.exchangerateapi.ERAPICurrencyLoader;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.CurrencyDialog;
import software.moneycalculator.gui.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private MoneyDialog moneyDialog;
    private SwingMoneyDisplay moneyDisplay;
    private CurrencyDialog currencyDialog;
    private final Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        List<Currency> currencies = new ERAPICurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                main.moneyDisplay(),
                new ERAPIExchangeRateLoader()
        );
        main.add("convert money", command);
        main.setVisible(true);
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }


    public SwingMain() throws HeadlessException {
        this.setTitle("Money calculator");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createMoneyDialog());
        this.add(createMoneyDisplay());
        this.add(createCurrencyDialog());
        this.add(toolBar());
    }

    private Component toolBar() {
        JPanel panel = new JPanel();
        JButton button = new JButton("convert");
        button.addActionListener(e -> {
            try {
                commands.get("convert money").execute();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(button);
        return panel;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    public SwingMoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }
    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}