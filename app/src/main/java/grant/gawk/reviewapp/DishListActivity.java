package grant.gawk.reviewapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class DishListActivity extends AppCompatActivity {
    ListView dishList;
    String restaurantName;
    Long restaurantID;
    ArrayAdapter<Dish> adapter;
    Context appContext;
    DataAccessObject dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);
        appContext = getApplicationContext();
        dao = new DataAccessObject(appContext);
        dao.open();

        //Sets Restaurant's name to toolbar title
        Intent intent = getIntent();
        Log.d("dishCLick", intent.getStringExtra("restaurantName"));
        setTitle(intent.getStringExtra("restaurantName"));
        restaurantName = intent.getStringExtra("restaurantName");
        restaurantID = intent.getLongExtra("restaurantID", 0L);


        dishList = (ListView) findViewById(R.id.dish_list);

        //This is the fix to the issue of the list not populating, and what I interpreted as transitioning not working
        populateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.open();
        populateList();
    }

    @Override
    protected void onPause(){
        dao.close();
        super.onPause();
    }

    private void populateList() {
        //get list of restaurants from Database and adapt them to list view
        final List<Dish> dishes = dao.getDishesFromRestaurant(restaurantID);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dishes);


        dishList.setAdapter(adapter);

        dishList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Log.d("Dish List Item" , dishes.get((int)id).toString());


                Dish clickedDish = dishes.get((int)id);
                adapter.notifyDataSetChanged();
                showDishForm(clickedDish);
            }
        });

    }

    //Overrides functionality to create overflow menu on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);

        return true;
    }

    //onClick function for settings menu
    public void openSettings(MenuItem item) {
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).addToBackStack(null).commit();
    }


    //called to move to Restaurant Data form.
    private void showDishForm(Dish dish){

        Intent intent = new Intent(this, ShowDishActivity.class);
        intent.putExtra("dishName", dish.getName());
        intent.putExtra("dishDate", dish.getDate());
        intent.putExtra("dishComments", dish.getComments());
        intent.putExtra("dishRating", Float.toString(dish.getRating()));
        startActivity(intent);

    }

    public void addDish(View view) {
        Intent intent = new Intent(this, AddDishActivity.class);
        intent.putExtra("restaurantName", restaurantName);
        startActivity(intent);
    }


}
