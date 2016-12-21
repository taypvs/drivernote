package app.camnanglaixe.com.android.activities;

import android.app.Activity;
import android.content.Intent;

import app.camnanglaixe.com.android.Common.Constanst;

/**
 * Created by taypham on 30/11/2016.
 */
public class BaseActivity extends Activity {

    protected void init(){

    }

    protected void startSubActivity(String type, boolean isSpecialGrid, int i){
        Intent intent = null;
        if(type.toLowerCase().equals(Constanst.TYPE_1)) { // Default Type
            if(isSpecialGrid)
                intent = new Intent(getBaseContext(), ListSubTopicGridActivity.class);
            else
                intent = new Intent(getBaseContext(), ListSubTopicActivity.class);
        } else if(type.toLowerCase().equals(Constanst.TYPE_2)){ // Image Signal type
            intent = new Intent(getBaseContext(), ListSubTopicActivity.class);
        } else if(type.toLowerCase().equals(Constanst.TYPE_3)){ // HTML type
            intent = new Intent(getBaseContext(), ListSubTopicActivity.class);
        }
        if(intent!=null) {
            intent.putExtra("KEY_TOPIC", i);
            startActivity(intent);
        }
    }

    protected void startContentActivity(String type, String content){
        Intent intent = null;
        if(type.toLowerCase().equals(Constanst.TYPE_POST_1)) { // Default Type
            intent = new Intent(getBaseContext(), ContentDetailTextActivity.class);
        } else if(type.toLowerCase().equals(Constanst.TYPE_POST_2)){ // Definition List Type
            intent = new Intent(getBaseContext(), ContentDetailDefActivity.class);
        } else if(type.toLowerCase().equals(Constanst.TYPE_POST_3)){ // Image Signal List type
            intent = new Intent(getBaseContext(), ListSignActivity.class);
        } else if(type.toLowerCase().equals(Constanst.TYPE_POST_4)){ // PDF type
            intent = new Intent(getBaseContext(), ContentDetailPDFActivity.class);
        }
        if(intent!=null) {
            intent.putExtra("KEY_CONTENT", content);
            startActivity(intent);
        }
    }

}
