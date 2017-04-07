package grant.gawk.reviewapp;


import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

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
        addPreferencesFromResource(R.xml.preferences);
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
        //Makes settings menu not have a transparent background.
//        getView().setBackgroundColor(ContextCompat.getColor(
//                getActivity().getApplicationContext(), R.color.colorAccent));
        try{
            getView().setBackgroundColor(Color.DKGRAY);}
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