package software.moneycalculator.gui.swing;

import software.moneycalculator.gui.AboutDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Properties;

public class SwingAboutDialog extends JDialog implements AboutDialog {

    private Properties properties;

    public SwingAboutDialog() {
        this.setTitle("About");
        this.setSize(400, 160);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
    }

    @Override
    public AboutDialog define(Properties properties) {
        this.properties = properties;
        add(createMainPanel());
        add(createToolBar());
        setVisible(true);
        return this;
    }

    private Component createToolBar() {
        JPanel panel = new JPanel();
        panel.add(createCopyButton());
        panel.add(createCloseButton());
        return panel;
    }

    private Component createCloseButton() {
        JButton button = new JButton("Close");
        button.addActionListener(e -> dispose());
        return button;
    }

    private Component createCopyButton() {
        JButton button = new JButton("Copy");
        button.addActionListener(e -> {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(aboutMessage()), null);
            JOptionPane.showMessageDialog(null, "Copied to clipboard", "Information", JOptionPane.INFORMATION_MESSAGE);
        });
        return button;
    }

    private Component createMainPanel() {
        JPanel panel = new JPanel();
        panel.add(appIcon());
        panel.add(createTextArea());
        return panel;
    }

    private Component createTextArea() {
        JTextArea textArea = new JTextArea(aboutMessage());
        textArea.setEditable(false);
        textArea.setFocusable(false);
        return textArea;
    }

    private String aboutMessage() {
        return appName() + " " + appVersion() + "\n" +
                appAuthor() + "\n" +
                appAuthorUrl() + "\n";
    }

    private Component appIcon() {
        Image image = new ImageIcon(appIconLocation()).getImage();
        return new JLabel(new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
    }

    private String appIconLocation() {
        return properties.getProperty("app.icon");
    }

    public String appName() {
        return properties.getProperty("app.name");
    }

    public String appVersion() {
        return properties.getProperty("app.version");
    }

    public String appAuthor() {
        return properties.getProperty("app.author");
    }

    public String appAuthorUrl() {
        return properties.getProperty("app.author.url");
    }
}
