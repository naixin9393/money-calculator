package software.moneycalculator.exchangerateapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.moneycalculator.Currency;
import software.moneycalculator.ExchangeRate;
import software.moneycalculator.ExchangeRateLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ERAPIExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) throws IOException {
        URL url = new URL(String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", ExchangeRateAPI.key(), from.acronym(), to.acronym()));
        String json = loadJson(url);
        return new ExchangeRate(
                from,
                to,
                LocalDate.parse(
                        new Gson()
                            .fromJson(json, JsonObject.class)
                            .get("time_last_update_utc")
                            .getAsString(),
                        DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
                ),
                new Gson()
                    .fromJson(json, JsonObject.class)
                    .get("conversion_rate")
                    .getAsDouble()
        );
    }

    private String loadJson(URL url) throws IOException {
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
