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
    public static String IS_FIRST_TIME_LAUNGH = "IS_FIRST_TIME_LAUNGH";

    public static void saveTopic(Context context, String key, String detail){
        //Creating a shared preference
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(key, detail);
        prefsEditor.commit();
    }

    public static String getTopic(Context context, String key){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString(key, "");
    }

    public static void saveFirstTimeLaungh(Context context){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(IS_FIRST_TIME_LAUNGH, true);
        prefsEditor.commit();
    }

    public static boolean isFirstTimeLaungh(Context context){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getBoolean(IS_FIRST_TIME_LAUNGH, false);
    }

}
