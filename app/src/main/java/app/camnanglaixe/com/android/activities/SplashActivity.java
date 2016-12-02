package app.camnanglaixe.com.android.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import app.camnanglaixe.com.android.Common.CommonUtils;
import app.camnanglaixe.com.android.Common.Constanst;
import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.apiservices.LoadFullJsonWebservice;
import app.camnanglaixe.com.android.models.FullTopics;
import app.camnanglaixe.com.android.network.ResponseCallbackInterface;

/**
 * Created by taypham on 30/11/2016.
 */
public class SplashActivity extends BaseActivity implements ResponseCallbackInterface {

    LoadFullJsonWebservice loadAllws;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        new LoadFileJsonTask().execute("");

    }

    private void init() {
        if (PreferenceUtils.isFirstTimeLaungh(getBaseContext())) {
            if (CommonUtils.isOnline(getBaseContext())) {
                loadAllws = new LoadFullJsonWebservice(this, this);
                loadAllws.doLoadAPI();
            } else {
                new LoadFileJsonTask().execute("");
            }
        } else {
//            PreferenceUtils.saveFirstTimeLaungh(getBaseContext());

        }
    }

    @Override
    public void onResultSuccess(Object result, String TAG) {
        Log.d("TayPVS", "TayPVS - onResultSuccess : " + result.toString());
        switch (TAG) {
            case Constanst.TAG_API_GET_FULL_INFO:
                ((TextView) findViewById(R.id.test)).setText(result.toString());
                // Save JSON from server to Internal Files
                FullTopics fullTopics = new FullTopics(getBaseContext(), (JSONArray) result);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onResultFail(Object resultFail, String TAG) {
        Log.d("TayPVS", "TayPVS - onResultFail : " + resultFail.toString());
        switch (TAG) {
            case Constanst.TAG_API_GET_FULL_INFO:
                new LoadFileJsonTask().execute("");
                break;
        }
    }

    // Load File From JSON TEST
    private class LoadFileJsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            try {
                // Load JSon From Asset
                JSONArray jsonObject = new JSONArray(CommonUtils.loadJSONFromAsset(getBaseContext(), Constanst.FILE_JSON_TEST));
                FullTopics testTopic = new FullTopics(getBaseContext(), jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }

    }


}


