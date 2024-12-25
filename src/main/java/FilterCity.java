package work9;

public class FilterCity extends Filter {

    private String city;

    public FilterCity(String city) {
        this.city = city;
    }

    public boolean check(HotelRoom room) {
        return room.city.equals(city);
    }

}
