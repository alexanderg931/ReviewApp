package grant.gawk.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;
import java.util.ArrayList;

/**
 * <p>
 *     The activity used to list Restaurants.
 * </p>
 * @author Anthony
 * @version 1.0
 * @since 1.0
 * @see FileHandler
 * @see AddRestaurantActivity
 * @see ShowDishActivity
 */
public class RestaurantListActivity extends AppCompatActivity {

    /**
     * <p>
     *     The ListView we will be putting restaurants in.
     * </p>
     */
    ListView restaurantList;

    /**
     * <p>
     *     An ArrayAdapter used for something. Anthony needs to explain this.
     * </p>
     */
    ArrayAdapter adapter;

    /**
     * <p>
     *     The current context of the application. Used for File Handling purposes
     * </p>
     */
    Context appContext;

    /**
     * <p>
     *     Initializes the ListView attribute for the restaurant, initializes the appContext attribute,
     *     and calls populateList() to fill the list with contents.
     * </p>
     * @param savedInstanceState The Bundle object passed by the calling function
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        //get reference to restaurant list and populate it.
        restaurantList = (ListView) findViewById(R.id.restaurant_list);
        appContext = this.getApplicationContext();
        populateList();

    }


//    //onClick function for settings menu
//    public void openSettings(MenuItem item) {
//        getFragmentManager().beginTransaction().replace(android.R.id.content,
//                new SettingsFragment()).addToBackStack(null).commit();
//    }
//
//
//
//    //Overrides functionality to create overflow menu on toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar, menu);
//
//        return true;
//    }


    /**
     * <p>
     *     The method used to populate the list of restaurants. Uses an onclick listener to
     *     record when the user interacts with a list element, and sends the index to getData().
     *     <br />
     *     <br />
     *     Once it is processed by getData(), showRestaurantForm() is called to open the list of dishes for
     *     that restaurant.
     * </p>
     * @see RestaurantListActivity#getData()
     * @see RestaurantListActivity#showRestaurantForm(View, String)
     */
    private void populateList(){
        adapter = new ArrayAdapter<>(this,
                //android.R.layout.simple_list_item_1, RestaurantData.getData(this.getApplicationContext())); //future
                android.R.layout.simple_list_item_1, getData());

        setTitle("My Restaurants");
        restaurantList.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Log.d("onClick" , parent.toString());
                Log.d("onClick" , v.toString());
                Log.d("onClick" , Integer.toString(position));
                Log.d("onClick" , Long.toString(id));
                Log.d("onClick" , getData().get((int)id));

                String restaurantName = getData().get((int)id); //get name of selected restaurant
                adapter.notifyDataSetChanged();
                showRestaurantForm(v, restaurantName);

            }
        });


    }

    /**
     * <p>
     *      Creates an intent and sends the name of the restaurant as an extra. Then opens the
     *      DishListActivity.
     * </p>
     * @param View              The active view of the program.
     * @param restaurantName    The name of the restaurant.
     * @see DishListActivity
     */
    private void showRestaurantForm(View View,  String restaurantName){
        Intent intent = new Intent(this, DishListActivity.class);
        intent.putExtra("restaurantName", restaurantName);
        startActivity(intent);
    }

    /**
     * <p>
     *     Opens up the AddRestaurantActivity so that a new restaurant can be added.
     * </p>
     * @param view  The active view of the program.
     * @see AddRestaurantActivity
     */
    public void addRestaurant(View view){
        Intent intent = new Intent(this, AddRestaurantActivity.class);
        startActivity(intent);

    }

    /**
     * <p>
     *     Uses the FileHandler to fetch the restaurants ArrayList and then extracts the String
     *     name from each one, and places them into a new ArrayList of Strings.
     * </p>
     * @return  An ArrayList of Strings containing the names of the restaurant.
     * @see FileHandler
     */
    public ArrayList<String> getData() {
        FileHandler files = new FileHandler(appContext);
        ArrayList<String> restaurantNames = new ArrayList<>();
        ArrayList<Restaurant> restaurants = files.getRestaurants();

        if (restaurants != null) {
            for (int x = 0; x < restaurants.size(); x++) {
                restaurantNames.add(restaurants.get(x).getName());
            }
        }
        return restaurantNames;
    }

}
