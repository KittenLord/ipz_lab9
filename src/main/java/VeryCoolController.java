package work9;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class VeryCoolController {

    private static final int pageSize = 10;

	@GetMapping("/search")
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
        if(order == "stars") iterator = new IteratorStars(result);
        else iterator = new IteratorStars(result);

        iterator.skipn(pageSize * page);
        List<HotelRoom> rooms = iterator.nextn(pageSize);

        String component = "<div><a href=\"/room?id=%d\"><h2>Hotel %s at city %s</h2></a>   <p>★ %d</p>   <p>$ %d</p></div>";
        String roomComponents = "";

        for(HotelRoom room : rooms) {
            roomComponents += String.format(component, room.id, room.hotelName, room.city, room.stars, room.price);
        }

        model.addAttribute("rooms", roomComponents);
		return "search";
	}

	@GetMapping("/room")
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
	public String postHotelGet(Model model) {
        System.out.println("GET EGEET GETE GET GET GET");
        model.addAttribute("hotelRoom", new HotelRoom());
	    return "posthotel";
	}

	@PostMapping("/posthotel")
	public String postHotelSubmit(@ModelAttribute HotelRoom hotelRoom, Model model) {
        Database db = Database.get();
        hotelRoom.isBooked = false;
        db.addRoom(hotelRoom);
		return "index";
	}

	@GetMapping("/home")
	public String home() {
		return "index";
	}

}
