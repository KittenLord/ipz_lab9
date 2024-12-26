package work9;

import java.util.List;
import java.util.Collections;

/**
 *  The iterator that orders the rooms from highest stars to lowest
 */
public class IteratorStars extends Iterator {

    /**
     *  The internal index
     */
    private int index;

    /**
     *  The constructor for the iterator that orders rooms as stars decrease
     *  @param list The list of data
     */
    public IteratorStars(List<HotelRoom> list) {
        Collections.sort(list, (a, b) -> Integer.compare(b.stars, a.stars)); // descending
        this.list = list;
    }

    /**
     *  The method that checks if there is a next item
     *  @return True if yes
     */
    public boolean hasNext() { return index < list.size(); }

    /**
     *  The method that returns the next item
     *  @return The next item
     */
    public HotelRoom next() { return list.get(index++); }

}
