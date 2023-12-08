package software.moneycalculator.mocks;

import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeRate;
import software.moneycalculator.Money;
import software.moneycalculator.exchangerateapi.ERAPICurrencyLoader;
import software.moneycalculator.exchangerateapi.ERAPIExchangeRateLoader;

import java.io.IOException;
import java.util.List;

public class MockMain {
    public static void main(String[] args) throws IOException {
        List<Currency> currencies = new ERAPICurrencyLoader().load();
        System.out.println(currencies);
/*
        Money money = new Money(1, euro);
        ExchangeRate exchangeRate = new ERAPIExchangeRateLoader().load(money.currency(), aDollar);
        System.out.println(money + "=" + money.amount() * exchangeRate.amount() + aDollar);
*/
    }
}
