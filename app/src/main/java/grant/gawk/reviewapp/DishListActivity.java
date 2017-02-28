package grant.gawk.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DishListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);
    }

    private void populateList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, DishData.getData());

        ListView restaurantList = (ListView) findViewById(R.id.dish_list);
        restaurantList.setAdapter(adapter);

        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Log.d("onClick" , parent.toString());
                Log.d("onClick" , v.toString());
                Log.d("onClick" , Integer.toString(position));
                Log.d("onClick" , Long.toString(id));

                showDishForm(v);
            }
        });


    }

    //called to move to Restaurant Data form.
    private void showDishForm(View View){

        Intent intent = new Intent(this, ShowDishActivity.class);
        startActivity(intent);

    }
}
