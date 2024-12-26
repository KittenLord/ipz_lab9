package work9;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Random;

@Controller

/**
 *  The class that routes the requests
 */
public class VeryCoolController {

    /**
     *  The amount of rooms that you can see per page
     */
    private static final int pageSize = 10;

	@GetMapping("/search")
    /**
     *  A method that routes the request to the search page, does the search idk i dont care
     *  @param minStars Min stars
     *  @param maxStars Max stars
     *  @param minPrice Min price
     *  @param maxPrice Max price
     *  @param city City
     *  @param id Id (if specified ignore everything else)
     *  @param page Page (skip page*pageSize items)
     *  @param order The way to order
     *  @param model Model
     */
	public String search(
        @RequestParam(name="minStars", required=false, defaultValue="0") int minStars,
        @RequestParam(name="maxStars", required=false, defaultValue="9999999") int maxStars,
        @RequestParam(name="minPrice", required=false, defaultValue="0") int minPrice,
        @RequestParam(name="maxPrice", required=false, defaultValue="9999999") int maxPrice,
        @RequestParam(name="city", required=false, defaultValue="") String city,
        @RequestParam(name="id", required=false, defaultValue="-1") int id,

        @RequestParam(name="page", required=false, defaultValue="0") int page,
        @RequestParam(name="order", required=false, defaultValue="stars") String order,

        Model model
    ) {

        Filter f = new FilterAlways();
        Filter filter = f;

        filter = filter.then(new FilterIsBooked(false));
        filter = filter.then(new FilterStars(minStars, maxStars));
        filter = filter.then(new FilterPrice(minPrice, maxPrice));

        if(city.length() != 0) filter = filter.then(new FilterCity(city));
        if(id != -1) filter = filter.then(new FilterId(id));

        Database db = Database.get();
        List<HotelRoom> result = db.getFiltered(f);
        Iterator iterator;

             if(order.equals("stars"))        iterator = new IteratorStars(result);
        else if(order.equals("price"))   iterator = new IteratorPrice(result);

        else                        iterator = new IteratorStars(result);

        iterator.skipn(pageSize * page);
        List<HotelRoom> rooms = iterator.nextn(pageSize);

        String component = "<div class=\"listRoom\"><a href=\"/room?id=%d\"><h2>Hotel %s at city %s</h2></a>   <p>★ %d</p>   <p>$ %d</p></div>";
        String roomComponents = "";

        for(HotelRoom room : rooms) {
            roomComponents += String.format(component, room.id, room.hotelName, room.city, room.stars, room.price);
        }

        model.addAttribute("rooms", roomComponents);
		return "search";
	}

	@GetMapping("/room")
    /**
     *  The route to check the details of the room
     *  @param id The room id
     *  @param model Model
     */
	public String room(
        @RequestParam(name="id", required=false, defaultValue="0") int id,
        Model model
    ) {
        if(id == 0) return "index"; // TODO: error

        Filter filter = new FilterId(id);

        Database db = Database.get();
        List<HotelRoom> result = db.getFiltered(filter);
        if(result.size() <= 0) {
            return "index"; // TODO: error?
        }

        HotelRoom room = result.get(0);

        model.addAttribute("number", room.roomNumber);

        model.addAttribute("stars", room.stars);
        model.addAttribute("price", room.price);

        model.addAttribute("city", room.city);
        model.addAttribute("hotelName", room.hotelName);

		return "room";
	}

	@GetMapping("/book")
    /**
     *  A route that books the room and redirects you to home page
     *  @param id The id of the room to book
     *  @param model Model
     */
	public String book(
        @RequestParam(name="id", required=false, defaultValue="0") int id,
        Model model
    ) {
        if(id == 0) return "index"; // TODO: error

        Filter filter = new FilterId(id);

        Database db = Database.get();
        List<HotelRoom> result = db.getFiltered(filter);
        if(result.size() <= 0) {
            return "index"; // TODO: error?
        }

        HotelRoom room = result.get(0);

        if(room.isBooked) return "index";

        // NOTE: contact the hotel here
        room.isBooked = true;
        db.saveDatabase();

		return "index";
	}

	@GetMapping("/posthotel")
    /**
     *  A route to the form to post your room
     *  @param model Model
     */
	public String postHotelGet(Model model) {
        model.addAttribute("hotelRoom", new HotelRoom());
	    return "posthotel";
	}

	@PostMapping("/posthotel")
    /**
     *  A route to post your room to the database
     *  @param hotelRoom The room to be posted
     *  @param model Model
     */
	public String postHotelSubmit(@ModelAttribute HotelRoom hotelRoom, Model model) {
        Database db = Database.get();
        hotelRoom.isBooked = false;
        db.addRoom(hotelRoom);
		return "index";
	}

	@GetMapping("/home")
    /**
     *  The route to the home page
     */
	public String home() {
		return "index";
	}

    @GetMapping("/DEBUG_POPULATE")
    /**
     *  The debug route to generate some data
     */
    public String debugGenerate() {
       Random rng = new Random();

        Database db = Database.get();
        String[] hotelWords = { "Aboba", "Kamaz", "Pivo", "Chupa", "Zebra", "Kaban" };
        String[] cities = { "Kiev", "New York", "Odessa", "Tokyo", "Beijing", "Berlin" };
        for(int hotel = 0; hotel < 30; hotel++) {
            String word1 = hotelWords[rng.nextInt(hotelWords.length)];
            String word2 = hotelWords[rng.nextInt(hotelWords.length)];
            String hotelName = word1 + word2;

            int amountOfRooms = rng.nextInt(5) + 5;
            int maxStars = rng.nextInt(3) + 6;
            int priceMultiplier = rng.nextInt(3) + 10;
            int roomOffset = rng.nextInt(30) + 30;
            for(int roomIndex = 0; roomIndex < amountOfRooms; roomIndex++) {
                HotelRoom room = new HotelRoom();
                room.hotelName = hotelName;
                room.city = cities[rng.nextInt(cities.length)];
                room.stars = rng.nextInt(maxStars) + 1;
                room.price = (rng.nextInt(4) + 20) * priceMultiplier;
                room.isBooked = false;
                room.roomNumber = roomOffset++;
                db.addRoom(room);
            }
        }

        return "index";
    }

}
