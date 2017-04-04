package grant.gawk.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * <p>
 *     Shows the dish whose attributes were sent in the intent from the DishListActivity in a user-friendly way.
 * </p>
 * @author Grant
 * @version 1.0
 * @since 1.0
 * @see DishListActivity
 */
public class ShowDishActivity extends AppCompatActivity {

    /**
     * <p>
     *     The UI piece where the date the dish was submitted is displayed.
     * </p>
     */
    EditText dateWidget;

    /**
     * <p>
     *     The UI piece where the name of the dish is displayed.
     * </p>
     */
    EditText nameWidget;

    /**
     * <p>
     *     The UI piece where the comments from the user are displayed.
     * </p>
     */
    EditText commentsWidget;

    /**
     * <p>
     *     The UI piece where the rating of the dish is displayed.
     * </p>
     */
    RatingBar ratingWidget;

    /**
     * <p>
     *     Grabs the dish attributes from the intent's extras, as well as
     *     grabs each UI element we'll be modifying and references them in the *Widget fields.
     * </p>
     * @param savedInstanceState The Bundle object passed by the calling function
     */
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


        String dateToUse = intent.getStringExtra("dishDate");
        String nameToUse = intent.getStringExtra("dishName");
        String commentsToUse = intent.getStringExtra("dishComments");
        String ratingToUse = intent.getStringExtra("dishRating");

        dateWidget.setText(dateToUse);
        nameWidget.setText(nameToUse);
        commentsWidget.setText(commentsToUse);
        ratingWidget.setRating(Float.parseFloat(ratingToUse));
    }


}
