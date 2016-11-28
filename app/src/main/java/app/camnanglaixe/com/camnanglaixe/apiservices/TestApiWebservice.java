package app.camnanglaixe.com.camnanglaixe.apiservices;

import android.content.Context;
import android.net.Network;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import app.camnanglaixe.com.camnanglaixe.Common.Constanst;
import app.camnanglaixe.com.camnanglaixe.network.HttpVolleyConnector;
import app.camnanglaixe.com.camnanglaixe.network.ResponseCallbackInterface;

/**
 * Created by phamvietsontay on 11/27/16.
 */
public class TestApiWebservice extends HttpVolleyConnector{


    public TestApiWebservice(ResponseCallbackInterface respone, Context context){
        mContext = context;
        url = Constanst.API_TEST2;
        responeCallback = respone;
        mRequestQueue =  Volley.newRequestQueue(mContext.getApplicationContext());
    }

    public void doGetJSONTest(){
        doConnectingApi(Constanst.GET);
    }

}
