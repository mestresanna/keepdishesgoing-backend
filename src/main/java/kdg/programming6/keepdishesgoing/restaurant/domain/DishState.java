package kdg.programming6.keepdishesgoing.restaurant.domain;

public enum DishState {
    DRAFT,
    PUBLISHED,
    UNPUBLISHED,
    OUT_OF_STOCK;

    public boolean isPublished() {
        if (this.equals(PUBLISHED)) {
            return true;
        } else  {
            return false;
        }
    }
}
