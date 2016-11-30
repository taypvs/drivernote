package app.camnanglaixe.com.android.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import app.camnanglaixe.com.android.Common.CommonUtils;
import app.camnanglaixe.com.android.Common.Constanst;
import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.Topic;
import app.camnanglaixe.com.android.network.ResponseCallbackInterface;

/**
 * Created by taypham on 30/11/2016.
 */
public class SplashActivity extends BaseActivity implements ResponseCallbackInterface {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new LoadFileJsonTask().execute("");

    }

    @Override
    public void onResultSuccess(Object result, String TAG) {

    }

    @Override
    public void onResultFail(Object resultFail, String TAG) {

    }

    private class LoadFileJsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            try {
                // Load JSon From Asset
                JSONObject jsonObject = new JSONObject(CommonUtils.loadJSONFromAsset(getBaseContext(), Constanst.FILE_JSON_TEST));
                Topic testTopic = JsonParseMachine.parseTopic(jsonObject);

                // Convert topic to String and save
                Gson gson = new Gson();
                String json = gson.toJson(testTopic);
                PreferenceUtils.saveTopic(getBaseContext(), json, PreferenceUtils.TOPIC_NUMBER+"TEST");

                Log.d("TayPVS", "TayPVS - testTopic " + testTopic.subTopics.get(0).title);
            }catch (JSONException e){
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }

    }



}


