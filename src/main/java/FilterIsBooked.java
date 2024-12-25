package work9;

public class FilterIsBooked extends Filter {

    private boolean isBooked;

    public FilterIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean check(HotelRoom room) {
        return room.isBooked == this.isBooked;
    }

}
