package software.moneycalculator;

public record Currency(String name, String acronym, String symbol) {
    @Override
    public String toString() {
        return String.format("%s (%s-%s)", symbol, name, acronym);
    }
}
