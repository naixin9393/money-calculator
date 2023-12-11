package software.moneycalculator.gui.swing;


import software.moneycalculator.Command;
import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeMoneyCommand;
import software.moneycalculator.SwapCurrenciesCommand;
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
    private MoneyDialog fromMoneyDialog;
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
        main.add("swap currencies", new SwapCurrenciesCommand(
                main.moneyDialog(),
                main.currencyDialog
        ));
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
        this.add(createMainPanel());
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createMoneyDialog());
        panel.add(createToMoneyPanel());
        panel.add(toolBar());
        return panel;
    }

    private Component createToMoneyPanel() {
        JPanel panel = new JPanel();
        panel.add(createMoneyDisplay());
        panel.add(createCurrencyDialog());
        return panel;
    }

    private Component toolBar() {
        JPanel panel = new JPanel();
        JButton convertButton = new JButton("convert");
        convertButton.addActionListener(e -> commands.get("convert money").execute());
        panel.add(convertButton);
        JButton swapButton = new JButton("swap");
        swapButton.addActionListener(e -> commands.get("swap currencies").execute());
        panel.add(swapButton);
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
        this.fromMoneyDialog = dialog;
        return dialog;
    }

    public SwingMoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }
    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }
    public MoneyDialog moneyDialog() {
        return fromMoneyDialog;
    }
}