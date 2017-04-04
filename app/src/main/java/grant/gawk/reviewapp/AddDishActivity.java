package grant.gawk.reviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.view.View;
import java.text.DateFormat;
import java.util.Date;
import android.content.Intent;
import android.content.Context;

public class AddDishActivity extends AppCompatActivity {
    EditText dishNameWidget;
    EditText commentsWidget;
    EditText dateWidget;
    RatingBar dishRatingWidget;
    String restaurantName;
    Long restaurantID;
    Context appContext;
    DataAccessObject dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        setTitle("Add a Dish");
        appContext = this.getApplicationContext();
        dao = new DataAccessObject(appContext);
        dao.open();
        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("restaurantName");
        restaurantID = intent.getLongExtra("restaurantID", 0L);

        dishNameWidget = (EditText) findViewById(R.id.dishNameEntry);
        commentsWidget = (EditText) findViewById(R.id.commentTextBox);
        dateWidget = (EditText) findViewById(R.id.dateEntry);
        dishRatingWidget = (RatingBar) findViewById(R.id.dishRatingBar);

        //Date autogeneration
        Date newDate = new Date();
        String date = DateFormat.getDateInstance().format(newDate);
        dateWidget.setText(date);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.open();
    }

    @Override
    protected void onPause(){
        dao.close();
        super.onPause();
    }

    public void collectData(View view)
    {
        //collected Data
        String dishName = dishNameWidget.getText().toString();
        String comments = commentsWidget.getText().toString();
        String date = dateWidget.getText().toString();
        float dishRating = dishRatingWidget.getRating();

        dao.insertDish(restaurantID, dishName, date, comments, dishRating, null);

        Intent intent = new Intent(this, DishListActivity.class);
        intent.putExtra("restaurantName", restaurantName);
        intent.putExtra("restaurantID", restaurantID);
        startActivity(intent);

    }
}
