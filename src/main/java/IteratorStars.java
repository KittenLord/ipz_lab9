package work9;

import java.util.List;
import java.util.Collections;

public class IteratorStars extends Iterator {

    private int index;

    public IteratorStars(List<HotelRoom> list) {
        Collections.sort(list, (a, b) -> Integer.compare(b.stars, a.stars)); // descending
        this.list = list;
    }

    public boolean hasNext() { return index < list.size(); }
    public HotelRoom next() { return list.get(index++); }

}
