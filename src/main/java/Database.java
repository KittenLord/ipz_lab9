package work9;

import com.google.gson.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.lang.Exception;
import java.nio.charset.Charset;
import java.util.Random;

public class Database {

    private static Random random;
    private static Database instance;
    private Database() {}

    private static final String databasePath = "database.json";

    public static Database get() {
        if(instance == null) loadDatabase();
        return instance;
    }

    private static void loadDatabase() {
        try{

        random = new Random();
        instance = new Database();

        File file = new File(databasePath);
        file.createNewFile();
        String json = FileUtils.readFileToString(file);

        if(json.length() == 0) { json = "[]"; }

        Gson g = new Gson();
        HotelRoom[] data = g.fromJson(json, HotelRoom[].class);
        instance.data = new ArrayList<>(Arrays.asList(data));

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

    public void addRoom(HotelRoom room) {
        room.id = random.nextInt(1000000) + 1;
        data.add(room);
        saveDatabase();
    }

    public void saveDatabase() {
        File file = new File(databasePath);
        Gson g = new Gson();
        HotelRoom[] array = new HotelRoom[data.size()];
        data.toArray(array);
        String json = g.toJson(array);
        try{
        FileUtils.writeStringToFile(file, json, Charset.forName("UTF-8"));
        }catch(Exception e) { System.out.println(e.toString()); System.exit(1); }
    }
}
