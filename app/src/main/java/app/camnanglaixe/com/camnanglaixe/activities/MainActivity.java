package app.camnanglaixe.com.camnanglaixe.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.camnanglaixe.com.camnanglaixe.Common.Constanst;
import app.camnanglaixe.com.camnanglaixe.R;
import app.camnanglaixe.com.camnanglaixe.apiservices.TestApiWebservice;
import app.camnanglaixe.com.camnanglaixe.network.ResponseCallbackInterface;


public class MainActivity extends Activity implements ResponseCallbackInterface{

    TestApiWebservice apiTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TayPVS" , "TayPVS - MainActivity");
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
