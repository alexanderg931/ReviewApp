package grant.gawk.reviewapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
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
import android.Manifest.permission;
import java.util.List;

/**
 * <p>
 *     The activity used to list Restaurants.
 * </p>
 * @author Anthony
 * @version 1.0
 * @since 1.0
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
    ArrayAdapter<Restaurant> adapter;
    Context appContext;
    private static final String TAG = "Restaurant List";
    private DataAccessObject dao;
    private boolean isVisible;

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
        dao = new DataAccessObject(appContext);
        dao.open();
        populateList();
        isVisible = true;

        checkAndAskForPermissions();
    }

    /**
     * <p>
     *     Checks to see if the API is at least version 23. If so, checks to see if we have permissions we need, and if not, requests them.
     * </p>
     */
    private void checkAndAskForPermissions()
    {
        //checks if the API is greater than or equal to version 23, which adds the requestPermissions feature
        //APIs lower than this ask on install, and this feature doesn't exist.
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            String[] perms = new String[3];
            perms[0] = permission.CAMERA;
            perms[1] = permission.WRITE_EXTERNAL_STORAGE;
            perms[2] = permission.READ_EXTERNAL_STORAGE;
            //if any of these permissions are not present
            if((ContextCompat.checkSelfPermission(appContext, perms[0]) == -1) || (ContextCompat.checkSelfPermission(appContext, perms[1]) == -1) || (ContextCompat.checkSelfPermission(appContext, perms[2]) == -1))
            {
                //makes dialog box asking for the permissions listed in the array
                requestPermissions(perms, 1);
            }
        }
    }
    @Override
    protected void onResume(){
        dao.open();
        super.onResume();
        populateList();
        isVisible = true;
    }

    @Override
    protected void onPause(){
        dao.close();
        isVisible = false;
        super.onPause();
    }


    //onClick function for settings menu
    public void openSettings(MenuItem item) {
        isVisible = false;
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).addToBackStack(null).commit();
    }



    //Overrides functionality to create overflow menu on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);

        return true;
    }


    /**
     * <p>
     *     The method used to populate the list of restaurants. Uses an onclick listener to
     *     record when the user interacts with a list element, and sends the index to getData().
     *     <br />
     *     <br />
     *     Once it is processed by getData(), showRestaurantForm() is called to open the list of dishes for
     *     that restaurant.
     * </p>
     */
    private void populateList(){
        //get list of restaurants from Database and adapt them to list view
        final List<Restaurant> restaurants = dao.getAllRestaurants(appContext);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurants);
        restaurantList.setAdapter(adapter);

        //call when List item is clicked
        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Log.d("Restaurant List Item" , restaurants.get((int)id).toString());

                Restaurant clickedRestaurant = adapter.getItem((int) id);
                adapter.notifyDataSetChanged();
                showRestaurantForm(clickedRestaurant); //move to Dish list
            }
        });

        //call when item is Long Clicked
        restaurantList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View v, int position, final long id){
                Log.d(TAG, "Long Clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantListActivity.this);
                builder.setMessage("Delete Entry?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "User clicked delete");
                        if (restaurantList.getCount() > 0) {
                            Restaurant rest = adapter.getItem((int) id);
                            dao.deleteRest(rest);
                            adapter.remove(rest);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "User clicked Cancel");
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();

                dialog.show();
                return true;
            }
        });
    }

    //called to move to List of Dishes
    private void showRestaurantForm(Restaurant restaurant){
        Intent intent = new Intent(this, DishListActivity.class);
        intent.putExtra("restaurantName", restaurant.getName());
        intent.putExtra("restaurantId", restaurant.getId());

        startActivity(intent);
    }

    //called to move to add Restaurant Form
    /**
     * <p>
     *     Opens up the AddRestaurantActivity so that a new restaurant can be added.
     * </p>
     * @param view  The active view of the program.
     * @see AddRestaurantActivity
     */
    public void addRestaurant(View view){
        Intent intent = new Intent(this, AddRestaurantActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }
    /*
    //Override restaurant list to always close when back is pressed
    //fixes issue after adding several restaurants where you would have
    //to press back multiple times
    @Override
    public void onBackPressed(){
        Log.d(TAG, String.valueOf(isVisible));
        if (!isVisible){
            super.onBackPressed();
        }
        else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    */

}
