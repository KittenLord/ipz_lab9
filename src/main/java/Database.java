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

/**
 *  A class that decribes a database stored in a file
 */
public class Database {

    /**
     *  A random number generator for internal use
     */
    private static Random random;

    /**
     *  A singleton instance of the database
     */
    private static Database instance;

    /**
     *  A private constructor to ensure the singleton
     */
    private Database() {}

    /**
     *  The path where the database is stored
     */
    private static final String databasePath = "database.json";

    /**
     *  A method to get (or load) the database
     *  @return The database
     */
    public static Database get() {
        if(instance == null) loadDatabase();
        return instance;
    }

    /**
     *  A method to load the database
     */
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

    /**
     *  The internal representation of the database
     */
    private List<HotelRoom> data;

    /**
     *  The method to get entries from the database that pass the filter
     *  @param filter The filter being used
     *  @return The data that passed the filter
     */
    public List<HotelRoom> getFiltered(Filter filter) {
        List<HotelRoom> result = new ArrayList<>();
        for(HotelRoom room : data) {
            if(filter.filter(room)) result.add(room);
        }
        return result;
    }

    /**
     *  A method to add the room to the data and back up the database
     *  @param room The room to be added
     */
    public void addRoom(HotelRoom room) {
        room.id = random.nextInt(1000000) + 1;
        data.add(room);
        saveDatabase();
    }

    /**
     *  A method to save the database
     */
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
