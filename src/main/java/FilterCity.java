package work9;

/**
 *  A filter that filters the rooms based on their city
 */
public class FilterCity extends Filter {

    /**
     *  The city to be checked against
     */
    private String city;
    
    /**
     *  The constructor for the city filter
     *  @param city The city of the filter
     */
    public FilterCity(String city) {
        this.city = city;
    }

    /**
     *  The method that checks whether the room's city fits
     *  @param room The room to be checked
     *  @return True if the city of the room is the same as filter's
     */
    public boolean check(HotelRoom room) {
        return room.city.equals(city);
    }

}
