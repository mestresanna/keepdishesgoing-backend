package kdg.programming6.keepdishesgoing.restaurant.domain;

public enum PriceRange {
    CHEAP ("€"), REGULAR("€€"), EXPENSIVE ("€€€"), PREMIUM ("€€€€"), NONE ("No information");
    private String description;
    PriceRange(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
