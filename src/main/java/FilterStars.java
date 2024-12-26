package work9;

/**
 *  The filter that checks whether the room fits the range of stars
 */
public class FilterStars extends Filter {

    /**
     *  The minimum allowed amount of stars
     */
    private int minStars;

    /**
     *  The maximum allowed amount of stars
     */
    private int maxStars;

    /**
     *  The cosntructor for the filter
     *  @param minStars The min stars
     *  @param maxStars The max stars
     */
    public FilterStars(int minStars, int maxStars) {
        this.minStars = minStars;
        this.maxStars = maxStars;
    }

    /**
     *  The method that checks whether the room fits the stars requirement
     *  @param room The room
     *  @return True if room's star amount fits the specified range
     */
    public boolean check(HotelRoom room) {
        return room.stars >= minStars && room.stars <= maxStars;
    }

}
