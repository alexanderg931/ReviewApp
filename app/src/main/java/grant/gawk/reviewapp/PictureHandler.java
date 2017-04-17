package grant.gawk.reviewapp;

import android.content.Context;
import android.support.v4.content.FileProvider;
import android.net.Uri;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import java.io.File;
import java.io.IOException;

/**
 * Created by Grant on 4/13/2017.
 */

public class PictureHandler {

    private Context appContext;
    private File image;
    private Uri pictureURI;
    private String restaurant;
    private String dish;
    private final String PROVIDER = "com.android.app.grant.gawk.reviewapp.fileprovider";

    public PictureHandler(Context ac, String r, String d)
    {
        appContext = ac;
        restaurant = r;
        dish = d;
        createFileAndPrepareURI(1);
    }

    public PictureHandler(Context ac, String r)
    {
        appContext = ac;
        restaurant = r;
        createFileAndPrepareURI(0);
    }

    public PictureHandler()
    {
        //Picture handler being used for aux functions. No field initialization.
    }
    public void setRestaurant(String r)
    {
        restaurant = r;
    }

    public void setDish(String d)
    {
        dish = d;
    }

    public void createFileAndPrepareURI(int action)
    {
        if(action == 0)
        {
            image = new File(appContext.getExternalFilesDir(null), restaurant + ".jpg");
        }
        else {
            image = new File(appContext.getExternalFilesDir(null), restaurant + "-" + dish + ".jpg");
        }
        if(!image.exists())
        {
            try {
                image.createNewFile();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }

        pictureURI = FileProvider.getUriForFile(appContext, PROVIDER, image);
    }

    public File getImageFile() {
        return image;
    }

    public Uri getPictureURI() {
        return pictureURI;
    }

    /**
     * <p>
     *     Deletes the image's File object from the drive.
     * </p>
     */
    public void deletePicture() {
        try {
            image.delete();
            image = null;
        } catch (java.lang.SecurityException e) {
            System.err.println(e.toString());
        }
    }

    /**
     *
     * @param name  A string of just picturename.jpg
     */
    public void deletePicture(String name) {
        //just the name.jpg for name
        File deleteThis = new File(appContext.getExternalFilesDir(null), name);
        try {
            deleteThis.delete();
            deleteThis = null;
        } catch(SecurityException e){
            System.err.println(e.toString());
        }
    }

    /**
     * <p>
     *     Note: you can send a URI from a string by doing YourURIObject.parse(YourURIString)
     * </p>
     * @param picUri    The full URI object of the file
     */
    public void deletePicture(Uri picUri) {
        File deleteThis = new File(picUri.toString());
        try {
            deleteThis.delete();
            deleteThis = null;
        } catch(SecurityException e) {
            System.err.println(e.toString());
        }
    }

    public Bitmap makeBitmapAndResize(){
        Bitmap thePicture = BitmapFactory.decodeFile(image.getAbsolutePath());
        Bitmap resizedPicture = Bitmap.createScaledBitmap(thePicture, 100, 100, false);
        return resizedPicture;
    }

    public Bitmap makeBitmapAndResize(String path) {
        File img = new File(path);
        Bitmap thePicture;
        if (img.exists()) {
            image = img;
            thePicture = makeBitmapAndResize();
        }
        else {
            System.err.println("File could not be found!");
            thePicture = null;
        }
        return thePicture;
    }

    public String getFilePath(){
        String ret;
        if (image == null) {
            ret = null;
        }
        else {
            ret = image.getAbsolutePath();
        }
        return ret;
    }
}
