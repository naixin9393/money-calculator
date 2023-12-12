package software.moneycalculator.gui.swing;

import software.moneycalculator.gui.ExchangeRateUpdateLabel;
import software.moneycalculator.gui.ToMoneyPanel;
import software.moneycalculator.gui.commands.Command;
import software.moneycalculator.gui.FromMoneyPanel;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SwingMainFrame extends JFrame {
    private static final String ConfigFile = "config.properties";
    private static Properties properties = new Properties();
    private FromMoneyPanel fromMoneyPanel;
    private ToMoneyPanel toMoneyPanel;
    private ExchangeRateUpdateLabel exchangeRateUpdateLabel;
    private final Map<String, Command> commands = new HashMap<>();

    public SwingMainFrame() throws HeadlessException {
        try {
            properties.load(SwingMainFrame.class.getClassLoader().getResourceAsStream(ConfigFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTitle(properties.getProperty("app.name"));
        this.setSize(400, 300);
        this.setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setJMenuBar(createMenuBar());
        this.add(createMainPanel());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createRunMenu());
        menuBar.add(createHelpMenu());
        return menuBar;
    }

    private Component createHelpMenu() {
        JMenu menu = new JMenu("Help");
        menu.add(createAboutItem());
        return menu;
    }

    private Component createAboutItem() {
        JMenuItem item = new JMenuItem("About");
        item.addActionListener(e -> JOptionPane.showMessageDialog(this, aboutMessage(), "About " + properties.getProperty("app.name"), JOptionPane.INFORMATION_MESSAGE));
        return item;
    }

    private String aboutMessage() {
        return properties.getProperty("app.name") + " " + properties.getProperty("app.version") + "\n" +
                properties.getProperty("app.author") + "\n" +
                properties.getProperty("app.author.url") + "\n";
    }

    private Component createRunMenu() {
        JMenu menu = new JMenu("Run");
        menu.add(createConvertItem());
        menu.add(createSwapItem());
        return menu;
    }

    private Component createSwapItem() {
        JMenuItem item = new JMenuItem("Swap");
        item.addActionListener(e -> commands.get("swap currencies").execute());
        return item;
    }

    private Component createConvertItem() {
        JMenuItem item = new JMenuItem("Convert");
        item.addActionListener(e -> commands.get("convert money").execute());
        return item;
    }

    private Component createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.add(createExitItem());
        return menu;
    }

    private Component createExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(e -> this.dispose());
        return item;
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

    public ToMoneyPanel toMoneyPanel() {
        return toMoneyPanel;
    }

    public ExchangeRateUpdateLabel exchangeRateUpdateLabel() {
        return exchangeRateUpdateLabel;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }
}