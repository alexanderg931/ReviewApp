package grant.gawk.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.TextView;
import android.view.View;

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
    TextView dateWidget;

    /**
     * <p>
     *     The UI piece where the name of the dish is displayed.
     * </p>
     */
    EditText nameWidget;

    PictureHandler hans;
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

    ImageView imageViewWidget;

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

        dateWidget = (TextView) findViewById(R.id.dateEntryDisplay);
        nameWidget = (EditText) findViewById(R.id.dishNameDisplay);
        commentsWidget = (EditText) findViewById(R.id.commentTextBoxDisplay);
        ratingWidget = (RatingBar) findViewById(R.id.dishRatingBarDisplay);
        imageViewWidget = (ImageView) findViewById(R.id.imageViewDisplay);
        hans = new PictureHandler();

        String dateToUse = intent.getStringExtra("dishDate");
        String nameToUse = intent.getStringExtra("dishName");
        String commentsToUse = intent.getStringExtra("dishComments");
        String ratingToUse = intent.getStringExtra("dishRating");
        String picture = intent.getStringExtra("dishPicture");
        System.out.println("Here: " + picture);
        dateWidget.setText(dateToUse);
        nameWidget.setText(nameToUse);
        commentsWidget.setText(commentsToUse);
        ratingWidget.setRating(Float.parseFloat(ratingToUse));

        //Checks if the picture is not null, if not, puts the picture into the imageView
        //if it is null, hides picture label
        if(picture != null){
            //imageViewWidget.setImageURI(Uri.parse(picture));
            //imageViewWidget.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewWidget.setImageBitmap(hans.makeBitmapAndResize(picture));
        }
        else {
            TextView PictureLabel = (TextView) findViewById(R.id.pictureDisplayLabel);
            PictureLabel.setVisibility(View.INVISIBLE);
        }
    }

    public void resubmit(View view){
        //need code here to delete previous entry from DAO. Can't figure out a way without somehow stealing its cursor object.
    }


}
