package software.moneycalculator.mocks;

import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeRate;
import software.moneycalculator.ExchangeRateLoader;

import java.time.LocalDate;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(from, to, LocalDate.now(), 2);
    }
}
