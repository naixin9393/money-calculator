package software.moneycalculator;

public record Currency(String name, String acronym) {
    @Override
    public String toString() {
        return String.format("%s (%s)", name, acronym);
    }
}
