package app.camnanglaixe.com.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import app.camnanglaixe.com.android.Common.Constanst;
import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.Topic;

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
        } else if(type.toLowerCase().equals(Constanst.TYPE_3)){ // 1 Trang thong tin
            try {
                JSONObject jsonObject = new JSONObject(PreferenceUtils.getString(getBaseContext(), PreferenceUtils.TOPIC_NUMBER + i));
                Log.d("TayPVS", "TayPVS - subtopic - jsonObject " + jsonObject.toString());
                Topic currentTopic = JsonParseMachine.parseTopic(jsonObject);
                Gson gson = new Gson();
                String json = gson.toJson(currentTopic.small_topic.get(0));
                startContentActivity(Constanst.TYPE_POST_1, json);
            } catch (Exception e){
                e.printStackTrace();
            }
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
