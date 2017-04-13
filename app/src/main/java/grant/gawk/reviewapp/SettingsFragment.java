package grant.gawk.reviewapp;


import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import static android.content.ContentValues.TAG;

/**
 * <p>
 *     The settings fragment used to start our sorts for the dishes.
 * </p>
 * @author Anthony
 * @version 1.0
 * @since 1.0
 * @see DishListActivity
 */
public class SettingsFragment extends PreferenceFragment {

    /**
     * <p>
     *     Loads settings from XML.
     * </p>
     * @param savedInstanceState The Bundle object passed by the calling function.
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
    }
    /**
     * <p>
     *     Sets the background color to gray, and catches NullPointerException.
     * </p>
     * @param savedInstanceState The Bundle object passed by the calling function.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
//        Makes settings menu not have a transparent background.
//        getView().setBackgroundColor(ContextCompat.getColor(
//                getActivity().getApplicationContext(), R.color.primary_material_light_1));
        try{
            getView().setBackgroundColor(Color.WHITE);}
        catch(NullPointerException e){
            Log.d(TAG, "onActivityCreated: NullPointerThrown");
        }
    }

    /**
     * <p>
     *     Resumes the DishListActivity on the destruction of this Activity and calls the onResume() method
     *     to refresh the list.
     * </p>
     * @see DishListActivity
     */
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG, "destroyed");
        //repopulates list by calling onResume method during recreate
        getActivity().recreate();
    }



}