package software.moneycalculator;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import software.moneycalculator.exchangerateapi.ERAPICurrencyLoader;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;
import software.moneycalculator.gui.commands.ExchangeMoneyCommand;
import software.moneycalculator.gui.commands.SwapCurrenciesCommand;
import software.moneycalculator.gui.swing.SwingMainFrame;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            FlatCarbonIJTheme.setup();
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        SwingMainFrame main = new SwingMainFrame();
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
}
