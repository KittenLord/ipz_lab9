package work9;

/**
 *  A filter that always passes
 */
public class FilterAlways extends Filter {

    /**
     *  A filter that always passes the room
     *  @param room The room in question
     *  @return Always true
     */
    public boolean check(HotelRoom room) { return true; }

}
