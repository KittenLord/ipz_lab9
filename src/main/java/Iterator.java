package work9;

import java.util.List;
import java.util.ArrayList;
    
public abstract class Iterator {

    protected List<HotelRoom> list;
    
    public abstract HotelRoom next();
    public abstract boolean hasNext();

    public List<HotelRoom> nextn(int n) {
        List<HotelRoom> result = new ArrayList<>();
        while(hasNext() && (n--) > 0) { result.add(next()); }
        return result;
    }
    
    public void skipn(int n) {
        nextn(n);
    }
}
