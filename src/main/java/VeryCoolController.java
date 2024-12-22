package work9;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VeryCoolController {

    private static final int pageSize = 10;

	@GetMapping("/search")
	public String search(
        @RequestParam(name="minStars", required=false, defaultValue="-1") int minStars,
        @RequestParam(name="maxStars", required=false, defaultValue="-1") int maxStars,
        @RequestParam(name="minPrice", required=false, defaultValue="-1") int minPrice,
        @RequestParam(name="maxPrice", required=false, defaultValue="-1") int maxPrice,
        @RequestParam(name="city", required=false, defaultValue="") String city,
        @RequestParam(name="id", required=false, defaultValue="-1") int id,

        @RequestParam(name="page", required=false, defaultValue="0") int page,
        @RequestParam(name="order", required=false, defaultValue="stars") String order
    ) {

        Filter filter = new FilterAlways();
        if(minStars != -1) filter = filter.then(new FilterStars(minStars, maxStars));
        if(minPrice != -1) filter = filter.then(new FilterPrice(minPrice, maxPrice));
        if(city != "") filter = filter.then(new FilterCity(city));
        if(id != -1) filter = filter.then(new FilterId(id));

        Database db = Database.get();
        List<HotelRoom> result = db.getFiltered(filter);
        Iterator iterator;
        if(order == "stars") iterator = new IteratorStars(result);
        else iterator = new IteratorStars(result);

        iterator.skipn(pageSize * page);
        List<HotelRoom> rooms = iterator.nextn(pageSize);

		return "search";
	}

	@GetMapping("/post")
	public String post() {
		return "post";
	}

	@GetMapping("/home")
	public String home() {
		return "index";
	}

}
