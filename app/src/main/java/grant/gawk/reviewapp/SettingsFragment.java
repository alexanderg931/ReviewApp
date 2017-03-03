package grant.gawk.reviewapp;


import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Load Settings from XML
        addPreferencesFromResource(R.xml.preferences);
    }

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
}