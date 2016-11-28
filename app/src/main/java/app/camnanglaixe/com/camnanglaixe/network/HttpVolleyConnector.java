package app.camnanglaixe.com.camnanglaixe.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.camnanglaixe.com.camnanglaixe.Common.Constanst;

/**
 * Created by phamvietsontay on 11/27/16.
 */
public class HttpVolleyConnector {
    public RequestQueue mRequestQueue;
    public Context mContext;
    public String url;

    public ResponseCallbackInterface responeCallback;

    public void doConnectingApi(String method){
        int methodRq = 0;
        if(method.equals(Constanst.GET))
            methodRq = Request.Method.GET;
        else if(method.equals(Constanst.POST))
            methodRq = Request.Method.POST;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (methodRq, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TayPVS","TayPVS response : " + response.toString());
                        responeCallback.onResultSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("TayPVS","TayPVS error : " + error.toString());
                        responeCallback.onResultFail(error);
                    }

                })
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
//                        headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }
        };

        mRequestQueue.add(jsObjRequest);
//        mRequestQueue.start();
    }

}
