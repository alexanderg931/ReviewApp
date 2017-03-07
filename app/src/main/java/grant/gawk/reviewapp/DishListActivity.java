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

public class DishListActivity extends AppCompatActivity {
    ListView dishList;
    String restaurantName;
    ArrayList<Dish> dishes;
    Context appContext;

    @Override
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

        //This is the fix to the issue of the list not populating, and what I interpreted as transitioning not working
        populateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

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
    private void showDishForm(View View, Dish dish){

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
