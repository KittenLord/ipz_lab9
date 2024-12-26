package work9;

/**
 *  The filter that checks whether the price fits in range
 */
public class FilterPrice extends Filter {

    /**
     *  The minimum allowed price
     */
    private int minPrice;

    /**
     *  The maximum allowed price
     */
    private int maxPrice;

    /**
     *  The constructor for the filter
     *  @param minPrice The min price
     *  @param maxPrice The max price
     */
    public FilterPrice(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    /**
     *  The method that checks whether the room passes the price requirements
     *  @param room The room to be checked
     *  @return True if room's price fits the specified range
     */
    public boolean check(HotelRoom room) {
        return room.price >= minPrice && room.price <= maxPrice;
    }

}
