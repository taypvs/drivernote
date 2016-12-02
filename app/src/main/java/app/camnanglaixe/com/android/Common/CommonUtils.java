package app.camnanglaixe.com.android.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * Created by taypham on 30/11/2016.
 */
public class CommonUtils {

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void saveObjectToFile(Context context, Object object, String fileName){
    // Convert topic to String and save
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Log.d("TayPVS", "TayPVS - saveObjectToFile : " + json);
        // write text to file
        try {
            FileOutputStream fileout = context.openFileOutput(fileName, context.MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(json);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
