package app.camnanglaixe.com.android.activities;

import android.app.Activity;
import android.content.Intent;

import app.camnanglaixe.com.android.Common.Constanst;

/**
 * Created by taypham on 30/11/2016.
 */
public class BaseActivity extends Activity {

    protected void startSubActivity(String type, int i){
        Intent intent = null;
        if(type.toLowerCase().equals(Constanst.TYPE_1)) { // Default Type
            intent = new Intent(getBaseContext(), ListSubTopicActivity.class);
        } else if(type.equals(Constanst.TYPE_2)){ // Image Signal type

        } else if(type.equals(Constanst.TYPE_3)){ // HTML type

        }
        if(intent!=null) {
            intent.putExtra("KEY_TOPIC", i);
            startActivity(intent);
        }
    }

}
