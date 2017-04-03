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
import java.util.ArrayList;
import android.content.Context;

/**
 * <p>
 *      The activity to display the list of dishes for a particular restaurant
 * </p>
 * @see FileHandler
 */
public class DishListActivity extends AppCompatActivity {
    /**
     * <p>
     *     Attribute to hold the ListView object from the activity
     * </p>
     */
    ListView dishList;

    /**
     * <p>
     *     Attribute to hold the name of the particular restaurant this list is for
     * </p>
     */
    String restaurantName;

    /**
     * <p>
     *     Array list to hold all the dishes for this particular restaurant
     * </p>
     */
    ArrayList<Dish> dishes;

    /**
     * <p>
     *     The application context
     * </p>
     */
    Context appContext;

    @Override
    /**
     * <p>
     *      Unwraps the intent from RestaurantListActivity to get the restaurant name.
     *      Also sets the title to the restaurant name and initializes the appContext, as well
     *      as the dishList and restaurantName fields.
     *      <br />
     *      <br />
     *      Makes a call to populateList() to initialize the listView's contents.
     * </p>
     * @param savedInstanceState  The bundle object passed by the calling function
     * @see RestaurantListActivity
     * @see DishListActivity#populateList()
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);
        appContext = getApplicationContext();

        //Sets Restaurant's name to toolbar title
        Intent intent = getIntent();
        Log.d("dishCLick", intent.getStringExtra("restaurantName"));
        setTitle(intent.getStringExtra("restaurantName"));
        restaurantName = intent.getStringExtra("restaurantName");

        dishList = (ListView) findViewById(R.id.dish_list);

        populateList();
    }

    @Override
    /**
     * <p>
     *     Updates the list on activity resume with a call to populateList().
     * </p>
     * @see DishListActivity#populateList()
     */
    protected void onResume() {
        super.onResume();
        populateList();
    }

    /**
     * <p>
     *     This method populates the data in the list. It use uses an on click listener to
     *     record if the user has interacted with the list, then takes the index, copies the
     *     dish object at that index in the ArrayList dishes, and sends the dish to the ShowDishActivity.
     * </p>
     *
     * <p>
     *     Makes a call to getData() to refresh the list.
     * </p>
     * @see ShowDishActivity
     * @see DishListActivity#getData()
     * @see DishListActivity#showDishForm
     */
    private void populateList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getData());

        dishList.setAdapter(adapter);

        dishList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Log.d("onClick" , parent.toString());
                Log.d("onClick" , v.toString());
                Log.d("onClick" , Integer.toString(position));
                Log.d("onClick" , Long.toString(id));

                getData(); //no assignment, it's just to refresh the list

                Dish dish = dishes.get((int)id);
                showDishForm(v, dish);
            }
        });

    }

    @Override
    /**
     * <p>
     *     Overrides functionality to create overflow menu on toolbar
     * </p>
     * @param menu The Menu object passed by the calling function
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);

        return true;
    }

    /**
     * <p>
     *     Our onClick function for the settings menu. Opens our settings fragment.
     * </p>
     * @param item The MenuItem object passed by the calling function
     */
    public void openSettings(MenuItem item) {
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).addToBackStack(null).commit();
    }

    /**
     * <p>
     *      The method to bring out the ShowDishActivity. Uses an intent and puts all the attributes
     *      of the dish object as extras to be unwrapped by the ShowDishActivity
     * </p>
     * @param View  The DishListActivity's view
     * @param dish  The Dish object to be presented on the next form
     * @see ShowDishActivity
     */
    private void showDishForm(View View, Dish dish){

        Intent intent = new Intent(this, ShowDishActivity.class);
        intent.putExtra("dishName", dish.getName());
        intent.putExtra("dishDate", dish.getDate());
        intent.putExtra("dishComments", dish.getComments());
        intent.putExtra("dishRating", Float.toString(dish.getRating()));
        startActivity(intent);

    }

    /**
     * <p>
     *     Sends the restaurant name as the extra in an intent, starts AddDishActivity
     * </p>
     * @param view The DishlistActivity's View
     * @see AddDishActivity
     */
    public void addDish(View view) {
        Intent intent = new Intent(this, AddDishActivity.class);
        intent.putExtra("restaurantName", restaurantName);
        startActivity(intent);
    }

    /**
     * <p>
     *      Fetches the dishes from file in FileHandler, and fetches the names of each dish from the
     *      ArrayList of dishes. Also makes a call to DishSort to sort the dishes.
     * </p>
     *
     * <p>
     *     <b>Note:</b> Updates the dishes attribute
     * </p>
     * @return The name of each dish in an ArrayList of Strings
     * @see DishSort
     * @see FileHandler#loadDishes(String)
     * @see FileHandler#getDishes()
     */
    public ArrayList<String> getData() {
        FileHandler files = new FileHandler(appContext);
        files.loadDishes(restaurantName);
        dishes = files.getDishes();
        dishes = DishSort.sort(dishes, appContext);
        ArrayList<String> dishNames = new ArrayList<>();

        if (dishes != null) {
            for (int x = 0; x < dishes.size(); x++) {
                dishNames.add(dishes.get(x).getName());
            }
        }
        return dishNames;

    }

}
