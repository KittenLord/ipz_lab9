package work9;

/**
 *  A class that describes a hotel room
 */
public class HotelRoom {

    /**
     *  The internal id of the room
     */
    public int id;

    /**
     *  Id setter
     */
    public void setId(int id) { this.id = id; }

    /**
     *  Id getter
     */
    public int getId() { return this.id; }


    /**
     *  The name of the hotel
     */
    public String hotelName;
    
    /**
     *  hotelname setter
     */
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    /**
     *  hotelName getter
     */
    public String getHotelName() { return hotelName; }


    /**
     *  The city where the room is located at
     */
    public String city;

    /**
     *  city setter
     */
    public void setCity(String city) { this.city = city; }

    /**
     *  city getter
     */
    public String getCity() { return city; }


    /**
     *  The amout of stars the room has
     */
    public int stars;

    /**
     *  stars setter
     */
    public void setStars(int stars) { this.stars = stars; }

    /**
     *  stars getter
     */
    public int getStars() { return stars; }


    /**
     *  The price for the room
     */
    public int price;

    /**
     *  price setter
     */
    public void setPrice(int price) { this.price = price; }

    /**
     *  price getter
     */
    public int getPrice() { return price; }


    /**
     *  Whether the room has already been booked
     */
    public boolean isBooked;

    /**
     *  isBooked setter
     */
    public void setIsBooked(boolean isBooked) { this.isBooked = isBooked; }

    /**
     *  isBooked getter
     */
    public boolean getIsBooked() { return this.isBooked; }


    /**
     *  The number of the room in the hotel
     */ 
    public int roomNumber;

    /**
     *  roomNumber setter
     */
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    /**
     *  roomNumber getter
     */
    public int getRoomNumber() { return roomNumber; }

}
