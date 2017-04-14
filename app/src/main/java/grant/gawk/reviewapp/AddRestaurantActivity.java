package grant.gawk.reviewapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v4.content.FileProvider;
import android.content.Context;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *     Activity to Add a new Restaurant. Makes use of FileHandler to write to file.
 *     <br />
 *     <br />
 *     <b>Note:</b> Picture support currently broken. Program will crash if attempted.
 * </p>
 * @author Khoa
 * @version 1.0
 * @since 1.0
 * @see RestaurantListActivity
 */

public class AddRestaurantActivity extends AppCompatActivity implements OnClickListener {

    /**
     * <p>
     *     Static field to verify if the image loaded
     * </p>
     */
    private static final int RESULT_LOAD_IMAGE = 1;

    /**
     * <p>
     *     The current application context.
     *
     * </p>
     */
    ImageView imageView;
    Context appContext;
    DataAccessObject dao;
    Uri pictureURI;
    /**
     * <p>
     *     The EditText where the user entered the restaurant name.
     * </p>
     */
    EditText restName;

    /**
     * <p>
     *     The EditText where the user entered the city name.
     * </p>
     */
    EditText cityName;


    /**
     * <p>
     *     Sets the title, loads the appContext attribute with the context, and grabs the city and restaurant name UI elements
     *      and places them into their EditText attributes.
     * </p>
     * @param savedInstanceState    The Bundle passed by the calling function.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        setTitle("Add a Restaurant");
        appContext = this.getApplicationContext();
        imageView = (ImageView) findViewById(R.id.imageView);
        cityName = (EditText) findViewById(R.id.cityName);
        restName = (EditText) findViewById(R.id.restName);
        dao = new DataAccessObject(appContext);
        dao.open();
    }

    @Override
    protected void onResume(){
        dao.open();
        imageView.setImageURI(pictureURI);
        super.onResume();
    }

    @Override
    protected void onPause(){
        dao.close();
        super.onPause();
    }

    /**
     * <p>
     *      The method responsible for two actions:
     *      <ul>
     *          <li>Opening the gallery</li>
     *          <li>Submitting the data to the FileHandler for writing, and returning to the RestaurantList</li>
     *      </ul>
     * </p>
     * <br />
     * Please note, that due to issues with obtaining the URI as a non-null reference from the gallery, the Restaurant object is created with a
     * null picture value in its constructor, so fixes to the image taking will need to be followed by a change of this null to the Bitmap object.
     * @param v     The current View.
     * @see RestaurantListActivity
     */
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.takePicture:
                PictureHandler hans = new PictureHandler(appContext, restName.getText().toString());
                pictureURI = hans.getPictureURI();
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                openCamera.putExtra(MediaStore.EXTRA_OUTPUT, pictureURI);
                startActivityForResult(openCamera, 1);
                break;

            case R.id.btnSubmit:
                String city = cityName.getText().toString();
                String restaurant = restName.getText().toString();
                String picturePath = pictureURI.toString(); //Temporary until we figure out how to store images

                dao.insertRestaurant(picturePath, restaurant, city);

                Intent returnToRestaurantList = new Intent(this, RestaurantListActivity.class);
                startActivity(returnToRestaurantList);
                break;
        }

    }
}

