package grant.gawk.reviewapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {
    ListView restaurantList;
    ArrayAdapter<Restaurant> adapter;
    Context appContext;
    private static final String TAG = "Restaurant List";
    private DataAccessObject dao;

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

    }

    @Override
    protected void onResume(){
        dao.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        dao.close();
        super.onPause();
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



    private void populateList(){
        //get list of restaurants from Database and adapt them to list view
        final List<Restaurant> restaurants = dao.getAllRestaurants();
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
    public void addRestaurant(View v){
        Intent intent = new Intent(this, AddRestaurantActivity.class);
        startActivity(intent);

    }



}
