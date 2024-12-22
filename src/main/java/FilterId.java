package work9;

public class FilterId extends Filter {

    private int id;

    public FilterId(int id) {
        this.id = id;
    }

    public boolean check(HotelRoom room) {
        return room.id == id;
    }

}
