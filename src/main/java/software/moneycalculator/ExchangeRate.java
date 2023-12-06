package software.moneycalculator;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, LocalDate date, double amount) {
}
