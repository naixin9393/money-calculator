package software.moneycalculator.exchangerateapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.moneycalculator.Currency;
import software.moneycalculator.CurrencyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ERAPICurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try {
            URL url = new URL(String.format("https://v6.exchangerate-api.com/v6/%s/codes", ExchangeRateAPI.key()));
            String json = loadJson(url);
            return toList(json);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<Currency> toList(String json) {
        List<JsonElement> supportedCodes = new Gson().fromJson(json, JsonObject.class)
                .get("supported_codes")
                .getAsJsonArray()
                .asList();

        return supportedCodes.stream()
                .map(entry -> new Currency(entry.getAsJsonArray().get(1).getAsString(), entry.getAsJsonArray().get(0).getAsString()))
                .collect(Collectors.toList());
    }

    private String loadJson(URL url) throws IOException {
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
