package software.moneycalculator.gui.swing;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import software.moneycalculator.gui.ExchangeRateUpdateLabel;
import software.moneycalculator.gui.ToMoneyPanel;
import software.moneycalculator.gui.commands.Command;
import software.moneycalculator.Currency;
import software.moneycalculator.gui.commands.ExchangeMoneyCommand;
import software.moneycalculator.gui.commands.SwapCurrenciesCommand;
import software.moneycalculator.exchangerateapi.ERAPICurrencyLoader;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.FromMoneyPanel;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private FromMoneyPanel fromMoneyPanel;
    private ToMoneyPanel toMoneyPanel;
    private ExchangeRateUpdateLabel exchangeRateUpdateLabel;
    private final Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        try {
            FlatCarbonIJTheme.setup();
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        SwingMain main = new SwingMain();
        List<Currency> currencies = new ERAPICurrencyLoader().load();
        main.add("swap currencies", new SwapCurrenciesCommand(
                main.fromMoneyPanel(),
                main.toMoneyPanel()
        ));
        main.add("convert money", new ExchangeMoneyCommand(
                new ERAPIExchangeRateLoader(), main.fromMoneyPanel().define(currencies),
                main.toMoneyPanel().define(currencies),
                main.exchangeRateUpdateLabel()
        ));
        main.setVisible(true);
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }

    public SwingMain() throws HeadlessException {
        this.setTitle("Money calculator");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createMainPanel());
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createFromMoneyPanel());
        panel.add(createToMoneyPanel());
        panel.add(createToolBar());
        panel.add(createExchangeRateUpdateLabel());
        return panel;
    }

    private Component createExchangeRateUpdateLabel() {
        SwingExchangeRateUpdateLabel label = new SwingExchangeRateUpdateLabel();
        this.exchangeRateUpdateLabel = label;
        return label;
    }

    private Component createToolBar() {
        JPanel panel = new JPanel();
        panel.add(createConvertButton());
        panel.add(createSwapCurrenciesButton());
        return panel;
    }

    private Component createSwapCurrenciesButton() {
        JButton button = new JButton("Swap");
        button.addActionListener(e -> commands.get("swap currencies").execute());
        return button;
    }

    private Component createConvertButton() {
        JButton button = new JButton("Convert");
        button.addActionListener(e -> commands.get("convert money").execute());
        return button;
    }

    private Component createFromMoneyPanel() {
        SwingFromMoneyPanel panel = new SwingFromMoneyPanel();
        this.fromMoneyPanel = panel;
        return panel;
    }

    private Component createToMoneyPanel() {
        SwingToMoneyPanel panel = new SwingToMoneyPanel();
        this.toMoneyPanel = panel;
        return panel;
    }

    public FromMoneyPanel fromMoneyPanel() {
        return fromMoneyPanel;
    }

    private ToMoneyPanel toMoneyPanel() {
        return toMoneyPanel;
    }

    public ExchangeRateUpdateLabel exchangeRateUpdateLabel() {
        return exchangeRateUpdateLabel;
    }
}