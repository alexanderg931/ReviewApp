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
import android.view.View.OnClickListener;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

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
     * </p>
     */
    Context appContext;

    /**
     * <p>
     *     The ImageView UI object
     * </p>
     */
    ImageView getImage;
    DataAccessObject dao;

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
        cityName = (EditText) findViewById(R.id.cityName);
        restName = (EditText) findViewById(R.id.restName);
        dao = new DataAccessObject(appContext);
        dao.open();
    }

    @Override
    protected void onResume(){
        dao.open();
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
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //Allow the gallery to be opened
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.btnSubmit:
                String city = cityName.getText().toString();
                String restaurant = restName.getText().toString();
                String picturePath = "No Picture"; //Temporary until we figure out how to store images

                dao.insertRestaurant(picturePath, restaurant, city);

                Intent returnToRestaurantList = new Intent(this, RestaurantListActivity.class);
                startActivity(returnToRestaurantList);
                break;
        }

    }

    /**
     * <p>
     *     The method that processes the picture URI obtained from the gallery
     *     <br />
     *     <br />
     *     <b>Note:</b> Currently gives null exceptions when the URI is obtained from the gallery. Will crash program on return.
     * </p>
     * @param requestCode   Integer to show if the image loaded
     * @param resultCode    Integer to show if the result is ok
     * @param data          The URI of the image
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data.getData() != null) //make sure to receive result from gallery
        {
            Uri selectedImage = data.getData(); //Get the uri, allows to prefer back to the image
            System.out.println(selectedImage.getPath());
            getImage.setImageURI(selectedImage); //display the image
        }
    }

}

