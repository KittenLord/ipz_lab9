package work9;

/**
 *  A class that describes a filter that checks whether a hotel room passes the requirements
 */
public abstract class Filter {

    /**
     *  The next filter in the chain
     */
    private Filter next;

    /**
     *  A method that combines two filters
     *  @param f The next filter
     *  @return The filter you passed to the method
     */
    public Filter then(Filter f) { next = f; return next; }

    /**
     *  A method to check whether the hotelroom passes the whole chain of the filters
     *  @param room The room to be checked
     *  @return True if the room passes
     */
    public boolean filter(HotelRoom room) {
        return check(room) && (next == null || next.filter(room));
    }

    /**
     *  The method that checks whether the room passes this specific filter
     *  @param room The room to be checked
     *  @return True if the room passes
     */
    public abstract boolean check(HotelRoom room);

}
