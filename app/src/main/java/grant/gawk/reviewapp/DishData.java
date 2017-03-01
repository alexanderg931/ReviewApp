package grant.gawk.reviewapp;

import java.util.ArrayList;
import java.util.Random;


public class DishData {

    private static ArrayList<String> myDishes = new ArrayList<>();

    //Generates dummy data for list view and returns the array
    public static ArrayList<String> getData(){
        Random rand = new Random();

        for(int i = 0; i<20; i++){

            myDishes.add("Dish #" + Integer.toString(rand.nextInt(999)));
        }

        return myDishes;
    }
}
