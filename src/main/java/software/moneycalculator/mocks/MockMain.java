package software.moneycalculator.mocks;

import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeRate;
import software.moneycalculator.Money;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;

import java.io.IOException;
import java.util.List;

public class MockMain {
    public static void main(String[] args) throws IOException {
        List<Currency> currencies = new MockCurrencyLoader().load();
        Currency euro = currencies.get(0);
        Currency dollar = currencies.get(1);
        Money money = new Money(130.13, euro);
        ExchangeRate exchangeRate = new ERAPIExchangeRateLoader().load(money.currency(), dollar);
        System.out.println(money + "=" + money.amount() * exchangeRate.amount() + dollar);
    }
}
