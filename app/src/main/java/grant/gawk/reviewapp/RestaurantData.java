package grant.gawk.reviewapp;

import java.util.ArrayList;
import java.util.Random;
//import android.content.Context;


public class RestaurantData {

    /* //To-do
    private static ArrayList<String> myRestaurants = new ArrayList<>();

    public static ArrayList<String> getData(Context context){


        // Test code. This whole class will be scrapped in favor of direct calls in the form
        FileHandler files = new FileHandler(context);
        Restaurant tortillas = new Restaurant(null, "Tortilla's", "La Porte");
        files.writeRestaurant(tortillas);
        Restaurant redlobster = new Restaurant(null, "Red Lobster", "Pasadena");
        files.writeRestaurant(redlobster);
        files.loadRestaurants();

        ArrayList<Restaurant> intermediate = files.getRestaurants();
        for(int x = 0; x < intermediate.size(); x++) {
            myRestaurants.add(intermediate.get(x).getName());
        }

        return myRestaurants;
    }
    */

    private static ArrayList<String> myRestaurants = new ArrayList<String>();

    //Generates dummy data for list view and returns the array
    public static ArrayList<String> getData(){
        Random rand = new Random();

        for(int i = 0; i<20; i++){

            myRestaurants.add("Restaurant #" + Integer.toString(rand.nextInt(999)));
        }

        return myRestaurants;
    }
}