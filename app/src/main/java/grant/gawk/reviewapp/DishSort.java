package grant.gawk.reviewapp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class DishSort {

    /**
     * Private constructor to prevent creation of sort objects.
     */
    private DishSort(){
    }

    public static ArrayList sort(ArrayList myList, Context context){

        String option = PreferenceManager.getDefaultSharedPreferences(context)
                .getString("pref_sortOption", "");
        Log.d("sortMethods", option);

        //determine type of sorting to be done
        switch (option) {
            //Sort A-Z
            case "A-Z":
                myList = azSort(myList);
                break;
            //Sort Z-A
            case "Z-A":
                myList = zaSort(myList);
                break;
            //Sort by Favorite
            case "Favorites":
                myList = favoriteSort(myList);
                break;
            //Sort by Recentness
            case "Recentness":
                myList = recentnessSort(myList);
                break;
            //Sort by Frequency
//            case "frequency":
//                myList = frequencySort(myList);
//                break;
        }

        return myList;
    }

    public static ArrayList azSort(ArrayList myList){
        Collections.sort(myList, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2)
            {

                return  dish1.getName().compareTo(dish2.getName());
            }
        });

        return myList;
    }

    public static ArrayList zaSort(ArrayList myList){
        Collections.sort(myList, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2)
            {

                return  dish2.getName().compareTo(dish1.getName());
            }
        });

        return myList;
    }

    public static ArrayList favoriteSort(ArrayList myList){
        Collections.sort(myList, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2)
            {

                return  dish1.isFavorite() - dish2.isFavorite();
            }
        });


        return myList;
    }
    public static ArrayList recentnessSort(ArrayList myList){
        Collections.sort(myList, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2)
            {
                //convert Date String to date object before comparing.
                Date d1 = new Date();
                Date d2 = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

                try {
                    d1 = sdf.parse(dish1.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    d2 = sdf.parse(dish2.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                return  d2.compareTo(d1);
            }
        });

        return myList;
    }
//    public static ArrayList frequencySort(ArrayList myList){
//        Collections.sort(myList, new Comparator<Dish>() {
//            @Override
//            public int compare(Dish dish1, Dish dish2)
//            {
//
//                return  dish1.getCount() - dish2.getCount();
//            }
//        });
//        return myList;
//    }

}
