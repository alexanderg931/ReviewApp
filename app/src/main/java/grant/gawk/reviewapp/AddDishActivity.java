package grant.gawk.reviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
//import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.Date;
public class AddDishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        EditText dateWidget = (EditText) findViewById(R.id.dateEntry);
        //This was annoying to find.
        Date newDate = new Date();
        String date = DateFormat.getDateInstance().format(newDate);
        dateWidget.setText(date);
    }

    public void collectData()
    {
        EditText dishNameWidget = (EditText) findViewById(R.id.dishNameEntry);
        EditText commentsWidget = (EditText) findViewById(R.id.commentTextBox);
        EditText dateWidget = (EditText) findViewById(R.id.dateEntry);
        RatingBar dishRatingWidget = (RatingBar) findViewById(R.id.dishRatingBar);

        //collected Data
        String dishName = dishNameWidget.getText().toString();
        String comments = commentsWidget.getText().toString();
        String date = dateWidget.getText().toString();
        float dishRating = dishRatingWidget.getRating();

        // create new Dish object to hold the data
        Dish newDish = new Dish(dishName, date, comments, dishRating);

    }
}
