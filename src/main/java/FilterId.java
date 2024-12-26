package work9;

/**
 *  A filter that checks whether the ids are the same
 */
public class FilterId extends Filter {

    /**
     *  The id to be compared to
     */
    private int id;

    /**
     *  A constructor for the id filter
     *  @param id The id
     */
    public FilterId(int id) {
        this.id = id;
    }

    /**
     *  The method that checks whether the ids are the same
     *  @param room The room to be checked
     *  @return True if the room id is the same
     */
    public boolean check(HotelRoom room) {
        return room.id == id;
    }

}
