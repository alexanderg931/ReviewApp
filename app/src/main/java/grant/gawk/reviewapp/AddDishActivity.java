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

/**
 * <p>
 *     Activity where the user enters the information about a particular dish.
 *     Image handling not yet functional. Called by DishListActivity, and uses FileHandler to
 *     add the new restaurant to file.
 * </p>
 * @author Grant
 * @version 1.0
 * @since 1.0
 * @see RestaurantListActivity
 * @see FileHandler
 */
public class AddDishActivity extends AppCompatActivity {

    /**
     * <p>
     *     The widget the user entered the dish name into.
     * </p>
     */
    EditText dishNameWidget;

    /**
     * <p>
     *     The widget the user entered their comments about the dish into
     * </p>
     */
    EditText commentsWidget;

    /**
     * <p>
     *     The autogenerated date of submission
     * </p>
     */
    EditText dateWidget;

    /**
     * <p>
     *     The 0-5 rating of the dish, entered by the user by clicking on the stars.
     * </p>
     */
    RatingBar dishRatingWidget;

    /**
     * <p>
     *     The name of the restaurant. Obtained from the intent.
     * </p>
     */
    String restaurantName;

    /**
     * <p>
     *     The context of this application. Necessary for file handling purposes.
     * </p>
     */
    Context appContext;

    /**
     * <p>
     *     This method initializes the appContext with the current context, extracts the restaurant name from the intent,
     *     and stores it in the restaurantName attribute, loads all of the UI elements into their specific attributes, and
     *     auto generates the date.
     * </p>
     * @param savedInstanceState The Bundle object passed by the calling function.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        setTitle("Add a Dish");
        appContext = this.getApplicationContext();
        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("restaurantName");

        dishNameWidget = (EditText) findViewById(R.id.dishNameEntry);
        commentsWidget = (EditText) findViewById(R.id.commentTextBox);
        dateWidget = (EditText) findViewById(R.id.dateEntry);
        dishRatingWidget = (RatingBar) findViewById(R.id.dishRatingBar);

        //Date autogeneration
        Date newDate = new Date();
        String date = DateFormat.getDateInstance().format(newDate);
        dateWidget.setText(date);
    }

    /**
     * <p>
     *     The method called by the submit button on the activity XML. This method extracts the user's data
     *     from the UI elements, creates a new Dish with them, and uses the FileHandler to write the Dish to
     *     the specific Restaurant's file. When it's done, it creates an intent to go back to the DishListActivity
     * </p>
     * @param view The current view, passed by the calling function
     */
    public void collectData(View view)
    {
        //collected Data
        String dishName = dishNameWidget.getText().toString();
        String comments = commentsWidget.getText().toString();
        String date = dateWidget.getText().toString();
        float dishRating = dishRatingWidget.getRating();

        // create new Dish object to hold the data
        Dish newDish = new Dish(dishName, date, comments, dishRating);

        /* portion to interface FileHandler should go here. Namely, feed it the new Dish, so to speak */
        FileHandler files = new FileHandler(appContext);
        files.writeDish(restaurantName, newDish);
        /* End File Handler Block */

        Intent transition = new Intent(this, DishListActivity.class);
        transition.putExtra("restaurantName", restaurantName);
        startActivity(transition);

    }
}
