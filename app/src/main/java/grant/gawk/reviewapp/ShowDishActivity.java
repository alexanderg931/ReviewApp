package grant.gawk.reviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

public class ShowDishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dish);

        EditText dateWidget = (EditText) findViewById(R.id.dateEntryDisplay);
        EditText nameWidget = (EditText) findViewById(R.id.dishNameDisplay);
        EditText commentsWidget = (EditText) findViewById(R.id.commentTextBoxDisplay);
        RatingBar ratingWidget = (RatingBar) findViewById(R.id.dishRatingBarDisplay);
        RatingBar serviceRatingWidget = (RatingBar) findViewById(R.id.serviceRatingBarDisplay);

        /* Code to extract from file goes here
        String dateToUse =
        String nameToUse =
        String commentsToUse =
        String ratingToUse =
        String serviceRatingToUse =

        dateWidget.setText();
        nameWidget.setText();
        commentsWidget.setText();
        ratingWidget.setRating();
        serviceRatingWidget.setRating();
        */
    }
}
