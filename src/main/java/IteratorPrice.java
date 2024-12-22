package work9;

import java.util.List;
import java.util.Collections;

public class IteratorPrice extends Iterator {

    private int index;

    public IteratorPrice(List<HotelRoom> list) {
        Collections.sort(list, (a, b) -> Integer.compare(a.price, b.price));
        this.list = list;
    }

    public boolean hasNext() { return index < list.size(); }
    public HotelRoom next() { return list.get(index++); }

}
