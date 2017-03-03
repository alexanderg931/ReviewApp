package grant.gawk.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DishListActivity extends AppCompatActivity {
    ListView dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        //Sets Restaurant's name to toolbar title
        Intent intent = getIntent();
        Log.d("dishCLick", intent.getStringExtra("restaurantName"));
        setTitle(intent.getStringExtra("restaurantName"));



        dishList = (ListView) findViewById(R.id.dish_list);

        //This is the fix to the issue of the list not populating, and what I interpreted as transitioning not working
        populateList();
    }

    private void populateList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, DishData.getData());

        dishList.setAdapter(adapter);

        dishList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Log.d("onClick" , parent.toString());
                Log.d("onClick" , v.toString());
                Log.d("onClick" , Integer.toString(position));
                Log.d("onClick" , Long.toString(id));

                String dishName = DishData.getData().get((int)id);
                showDishForm(v, dishName);
            }
        });



    }

    //called to move to Restaurant Data form.
    private void showDishForm(View View, String dishName){

        Intent intent = new Intent(this, ShowDishActivity.class);
        intent.putExtra("dishName", dishName);
        startActivity(intent);

    }

    public void addDish(View view) {
        Intent intent = new Intent(this, AddDishActivity.class);
        startActivity(intent);
    }
}
