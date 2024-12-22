package work9;

import com.google.gson.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.lang.Exception;

public class Database {

    private static Database instance;
    private Database() {}

    private static final String databasePath = "database.json";

    public static Database get() {
        if(instance == null) loadDatabase();
        return instance;
    }

    private static void loadDatabase() {
        try{

        instance = new Database();

        File file = new File(databasePath);
        file.createNewFile();
        String json = FileUtils.readFileToString(file);

        if(json.length() == 0) { json = "[]"; }

        Gson g = new Gson();
        HotelRoom[] data = g.fromJson(json, HotelRoom[].class);
        instance.data = Arrays.asList(data);

        }catch(Exception e) { System.out.println("I couldn't care less"); System.exit(1); }
    }



    private List<HotelRoom> data;

    public List<HotelRoom> getFiltered(Filter filter) {
        List<HotelRoom> result = new ArrayList<>();
        for(HotelRoom room : data) {
            if(filter.filter(room)) result.add(room);
        }
        return result;
    }
}
