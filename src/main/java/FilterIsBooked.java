package work9;

/**
 *  The filter that checks the status of room being booked
 */
public class FilterIsBooked extends Filter {

    /**
     *  The required status
     */
    private boolean isBooked;

    /**
     *  A constructor for the filter
     *  @param isBooked The asked status
     */
    public FilterIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    /**
     *  The method that checks whether the room passes the test
     *  @param room The room to be checked
     *  @return True if room's booked status is the same
     */
    public boolean check(HotelRoom room) {
        return room.isBooked == this.isBooked;
    }

}
