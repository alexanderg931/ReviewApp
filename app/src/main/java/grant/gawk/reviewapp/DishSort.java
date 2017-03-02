package grant.gawk.reviewapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class DishSort {

    private DishSort(){
        //Prevents creation of sort objects
    }

    public static ArrayList sort(ArrayList myList, String option){

        //determine type of sorting to be done
        switch (option) {
            //Sort A-Z
            case "a-z":
                myList = azSort(myList);
                break;
            //Sort by Favorite
            case "favorite":
                myList = favoriteSort(myList);
                break;
            //Sort by Recentness
            case "recent":
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

                return  dish1.getDate().compareTo(dish2.getDate());
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
