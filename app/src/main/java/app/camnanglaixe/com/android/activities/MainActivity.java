package app.camnanglaixe.com.android.activities;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListTopicAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.Topic;


public class MainActivity extends BaseActivity{

    private ListTopicAdapter listTopicAdapter;
    private List<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topics = new ArrayList<Topic>();
        try {
            for (int i = 0; i < 8; i++) {
                JSONObject jsonObject = new JSONObject(PreferenceUtils.getTopic(getBaseContext(), PreferenceUtils.TOPIC_NUMBER + i));
                Topic topic = JsonParseMachine.parseTopic(jsonObject);
                topics.add(topic);
            }
        }catch (JSONException e){

        }

    }

}
