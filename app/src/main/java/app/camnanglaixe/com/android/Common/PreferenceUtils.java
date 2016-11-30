package app.camnanglaixe.com.android.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Created by taypham on 30/11/2016.
 */
public class PreferenceUtils {

    public static String TOPIC_NUMBER = "TOPIC_NUMBER_";

    public static void saveTopic(Context context, String key, String detail){
        //Creating a shared preference
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(key, detail);
        prefsEditor.commit();
    }

}
