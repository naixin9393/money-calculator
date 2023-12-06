package software.moneycalculator.mocks;

import software.moneycalculator.Currency;
import software.moneycalculator.CurrencyLoader;

import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return List.of(
                new Currency("Euro", "EUR", "€"),
                new Currency("Dollar", "USD", "$")
        );
    }
}
