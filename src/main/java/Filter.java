package work9;

public abstract class Filter {

    private Filter next;
    public Filter then(Filter f) { next = f; return next; }

    public boolean filter(HotelRoom room) {
        return check(room) && (next == null || next.filter(room));
    }

    public abstract boolean check(HotelRoom room);

}
