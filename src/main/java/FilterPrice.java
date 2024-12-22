package work9;

public class FilterPrice extends Filter {

    private int minPrice;
    private int maxPrice;

    public FilterPrice(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public boolean check(HotelRoom room) {
        return room.price >= minPrice && room.price <= maxPrice;
    }

}
