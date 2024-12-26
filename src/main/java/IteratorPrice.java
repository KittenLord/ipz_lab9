package work9;

import java.util.List;
import java.util.Collections;

/**
 *  The class that describes an iterator that goes from lowest price to highest
 */
public class IteratorPrice extends Iterator {

    /**
     *  The current index of the iterator
     */
    private int index;

    /**
     *  The constructor that orders rooms to increase price
     *  @param list The list of data
     */
    public IteratorPrice(List<HotelRoom> list) {
        Collections.sort(list, (a, b) -> Integer.compare(a.price, b.price));
        this.list = list;
    }

    /**
     *  Checks whether there is a next room
     *  @return True if there is
     */
    public boolean hasNext() { return index < list.size(); }

    /**
     *  Returns the next room is there is one
     *  @return The next item
     */
    public HotelRoom next() { return list.get(index++); }

}
