package app.camnanglaixe.com.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import app.camnanglaixe.com.android.apiservices.TestApiWebservice;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.network.ResponseCallbackInterface;
import app.camnanglaixe.com.android.R;


public class MainActivity extends Activity implements ResponseCallbackInterface {

    TestApiWebservice apiTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParseMachine.parseFile(getBaseContext(), "jsonTest.txt");
        apiTest = new TestApiWebservice(this, this);
        apiTest.doGetJSONTest();
    }

    public void onResultSuccess(Object result){
        Log.d("TayPVS", "TayPVS - result " + result.toString());
    }

    public void onResultFail(Object resultFail){
        Log.d("TayPVS", "TayPVS - result " + resultFail.toString());
    }

}
