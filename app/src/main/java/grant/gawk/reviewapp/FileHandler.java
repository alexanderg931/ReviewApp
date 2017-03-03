package grant.gawk.reviewapp;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Grant on 2/27/2017.
 */
import android.content.Context;
import android.os.Environment;

public class FileHandler {
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Dish> dishList;
    private File rTxt;
    private File dTxt;
    private BufferedWriter output;
    private Scanner reader;
    private Context appContext;

    public FileHandler(Context context) {
        appContext = context;
        rTxt = new File(appContext.getExternalFilesDir(null), "restaurants.txt");
        if (!rTxt.exists()) {
            try {
                rTxt.createNewFile();
            }
            catch (IOException e) {
                System.err.println(e.toString());
            }
        }
        else {
            loadRestaurants();
        }
    }

    public void writeDish(String res, Dish dish) {
        dTxt = new File(appContext.getExternalFilesDir(null), res+".txt");

        if(!dTxt.exists())
        {
            try {
                dTxt.createNewFile();
            }
            catch (IOException e) {
                System.err.println(e.toString());
            }
        }
        else {
            try {
                output = new BufferedWriter(new FileWriter(dTxt, true));
                String toWrite = dish.getName() + "_" + dish.getDate() + "_" + dish.getComments() + "_" + Float.toString(dish.getRating());
                output.append(toWrite);
                output.newLine();
                output.close();
            }
            catch (IOException e)
            {
                System.err.println(e.toString());
            }
        }
    }

    public void writeDish(Restaurant res, Dish dish) {
        writeDish(res.getName(), dish);
    }

    public void writeRestaurant(Restaurant res) {
        //either the error lies in this function
        try {
            output = new BufferedWriter(new FileWriter(rTxt, true));
            String toWrite = res.getName() + "_" + res.getCity();
            System.out.println(toWrite);
            output.append(toWrite);
            output.newLine();
            output.close();
        }
        catch (IOException e)
        {
            System.err.println(e.toString());
        }

        loadRestaurants(); //commented out from error
    }

    public void loadRestaurants() {
        try {
            Scanner reader = new Scanner(rTxt);
            String inputBuffer;
            restaurantList = new ArrayList<>();
            while(reader.hasNextLine()) {
                String[] inputBroken;
                //but this is specifically where the error was coming from. reader.nextLine() was getting null exceptions, so file isn't being written to? file is empty and contents aren't being generated?
                //I'm skipping over a line? notes for later
                inputBuffer = reader.nextLine();
                inputBroken = inputBuffer.split("_");
                /* need to change this null when I figure out image handling */
                Restaurant newRes = new Restaurant(null, inputBroken[0], inputBroken[1]);
                restaurantList.add(newRes);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    public void loadDishes(String res) {
        /* Dishes loaded on demand for memory concerns, as opposed to restaurants which are front-loaded */
        try {
            dTxt = new File(appContext.getExternalFilesDir(null), res+".txt");

            if (!dTxt.exists()) {
                try {
                    dTxt.createNewFile();
                }
                catch (IOException e) {
                    System.err.println(e.toString());
                }
            }

            reader = new Scanner(dTxt);
            String inputBuffer;
            String[] inputBroken;
            dishList = new ArrayList<>();
            while (reader.hasNextLine()) {
                inputBuffer = reader.nextLine();
                inputBroken = inputBuffer.split("_");
                /* public Dish(String n, String d, String c, float r) */
                dishList.add(new Dish(inputBroken[0], inputBroken[1], inputBroken[2], Float.parseFloat(inputBroken[3])));
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    public void loadDishes(Restaurant res) {
        loadDishes(res.getName());
    }

    public ArrayList<Dish> getDishes() {
        return dishList;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurantList;
    }
}
