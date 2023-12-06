package software.moneycalculator.mocks;

import software.moneycalculator.Currency;
import software.moneycalculator.Money;

public class MockMain {
    public static void main(String[] args) {
        Currency euro = new Currency("Euro", "EUR", "€");
        Money money = new Money(130.13, euro);
        System.out.println(euro);
        System.out.println(money);
    }
}
