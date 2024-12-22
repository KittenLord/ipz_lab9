package work9;

public class FilterStars extends Filter {

    private int minStars;
    private int maxStars;

    public FilterStars(int minStars, int maxStars) {
        this.minStars = minStars;
        this.maxStars = maxStars;
    }

    public boolean check(HotelRoom room) {
        return room.stars >= minStars && room.stars <= maxStars;
    }

}
