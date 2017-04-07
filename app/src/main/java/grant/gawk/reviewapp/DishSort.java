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

/**
 * <p>
 *     Class used to sort Dishes by varying criteria.
 * </p>
 * @author Anthony
 * @version 1.0
 * @since 1.0
 * @see Dish
 */
public class DishSort {

    /**
     * Private constructor to prevent creation of DishSort objects. The sorts included are linked below.
     */
    private DishSort(){
    }

    /**
     * <p>
     *     Chooses the algorithm to use to sort the list by.
     * </p>
     * @param myList    The ArrayList of Dishes
     * @param context   The current application context
     * @return  A sorted list
     * @see DishSort#azSort(ArrayList)
     * @see DishSort#zaSort(ArrayList)
     * @see DishSort#recentnessSort(ArrayList)
     */
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
                //myList = favoriteSort(myList);
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

    /**
     * <p>
     *     Sort from a to z. Uses Collections.sort and Comparator to
     *     alphabetize the list.
     * </p>
     * @param myList    The ArrayList of Dish objects.
     * @return  The ArrayList, sorted from A to Z.
     */
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

    /**
     * <p>
     *     Sort from z to a. Uses Collections.sort and Comparator to accomplish this.
     * </p>
     * @param myList    The ArrayList of Dish objects.
     * @return  The ArrayList, sorted from z to a.
     */
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


//    public static ArrayList favoriteSort(ArrayList myList){
//        Collections.sort(myList, new Comparator<Dish>() {
//            @Override
//            public int compare(Dish dish1, Dish dish2)
//            {
//
//                return  dish1.isFavorite() - dish2.isFavorite();
//            }
//        });
//
//
//        return myList;
//    }

    /**
     * <p>
     *     Sorts the list by the recentness of their submission date. Uses Collections.sort and Comparator,
     *      but also SimpleDateFormat to parse the date string into a Date object to be compared. Also catches
     *      ParseExceptions from SimpleDateFormat if the parse fails.
     * </p>
     * @param myList    The ArrayList of Dish objects.
     * @return  The ArrayList, sorted by recentness.
     */
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
