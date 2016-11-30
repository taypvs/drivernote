package app.camnanglaixe.com.android.activities;

import android.os.Bundle;
import android.util.Log;

import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.apiservices.TestApiWebservice;
import app.camnanglaixe.com.android.network.ResponseCallbackInterface;


public class MainActivity extends BaseActivity implements ResponseCallbackInterface {

    TestApiWebservice apiTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        JsonParseMachine.parseFile(getBaseContext(), "jsonTest.txt");
        apiTest = new TestApiWebservice(this, this);
        apiTest.doGetJSONTest();
    }

    public void onResultSuccess(Object result, String TAG){
        Log.d("TayPVS", "TayPVS - result " + result.toString());
    }

    public void onResultFail(Object resultFail, String TAG){
        Log.d("TayPVS", "TayPVS - result " + resultFail.toString());
    }

}
