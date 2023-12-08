package software.moneycalculator.swing;


import javax.swing.*;
import java.awt.*;

public class SwingMain extends JFrame {
    public SwingMain() throws HeadlessException {
        this.setTitle("Money calculator");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SwingMain().setVisible(true);
    }
}
