package grant.gawk.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

public class ShowDishActivity extends AppCompatActivity {
    EditText dateWidget;
    EditText nameWidget;
    EditText commentsWidget;
    RatingBar ratingWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dish);

        //Sets Dish's name to toolbar title
        Intent intent = getIntent();
        Log.d("showDish", intent.getStringExtra("dishName"));
        setTitle(intent.getStringExtra("dishName"));

        dateWidget = (EditText) findViewById(R.id.dateEntryDisplay);
        nameWidget = (EditText) findViewById(R.id.dishNameDisplay);
        commentsWidget = (EditText) findViewById(R.id.commentTextBoxDisplay);
        ratingWidget = (RatingBar) findViewById(R.id.dishRatingBarDisplay);

        /* Code to extract from file goes here
        String dateToUse =
        String nameToUse =
        String commentsToUse =
        float ratingToUse =

        dateWidget.setText();
        nameWidget.setText();
        commentsWidget.setText();
        ratingWidget.setRating();
        */

        dummyData();
    }

    private void dummyData() {
        String dateToUse = "3/1/2017";
        String nameToUse = "Carne Asada";
        String commentsToUse = "Delicious";
        float ratingToUse = 5f;

        dateWidget.setText(dateToUse);
        nameWidget.setText(nameToUse);
        commentsWidget.setText(commentsToUse);
        ratingWidget.setRating(ratingToUse);
    }
}
