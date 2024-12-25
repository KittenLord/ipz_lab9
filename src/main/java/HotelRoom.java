package work9;

public class HotelRoom {

    public int id;
    public void setId(int id) { this.id = id; }
    public int getId() { return this.id; }

    public String hotelName;
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public String getHotelName() { return hotelName; }

    public String city;
    public void setCity(String city) { this.city = city; }
    public String getCity() { return city; }

    public int stars;
    public void setStars(int stars) { this.stars = stars; }
    public int getStars() { return stars; }

    public int price;
    public void setPrice(int price) { this.price = price; }
    public int getPrice() { return price; }

    public boolean isBooked;
    public void setIsBooked(boolean isBooked) { this.isBooked = isBooked; }
    public boolean getIsBooked() { return this.isBooked; }

    public int roomNumber;
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public int getRoomNumber() { return roomNumber; }

}
