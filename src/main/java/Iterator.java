package work9;

import java.util.List;
import java.util.ArrayList;
    
/**
 *  The class that describes the way to iterate over the hotel rooms
 */
public abstract class Iterator {

    /**
     *  The internal list
     */
    protected List<HotelRoom> list;
    
    /**
     *  The method to get the next room
     *  @return The next room
     */
    public abstract HotelRoom next();

    /**
     *  The method to check whether there is a next room
     *  @return True if there is next
     */
    public abstract boolean hasNext();


    /**
     *  The method that gets n or less next items
     *  @param n The max amount to get
     *  @return A list of at most n rooms
     */
    public List<HotelRoom> nextn(int n) {
        List<HotelRoom> result = new ArrayList<>();
        while(hasNext() && (n--) > 0) { result.add(next()); }
        return result;
    }
    
    /**
     *  The method to skip next n items
     *  @param n The amount of items to skip
     */
    public void skipn(int n) {
        nextn(n);
    }
}
