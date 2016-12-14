package app.camnanglaixe.com.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.camnanglaixe.com.android.Common.Constanst;
import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListTopicAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.Topic;


public class MainActivity extends BaseActivity{

    private ListTopicAdapter listTopicAdapter;
    private List<Topic> topics;
    private GridView topicGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    protected void init(){
        Log.d("TayPVS","TayPVS - Main");
        addTopics();
        topicGridView = (GridView) findViewById(R.id.mainGridLayout);
        listTopicAdapter = new ListTopicAdapter(this, topics);
        listTopicAdapter.notifyDataSetChanged();
        topicGridView.setAdapter(listTopicAdapter);
        topicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(MainActivity.this, ListSubTopicActivity.class);
//                intent.putExtra("KEY_TOPIC", i);
//                startActivity(intent);
                startSubActivity(topics.get(i).type_name, i);
            }
        });
    }

    private void addTopics(){
        topics = new ArrayList<Topic>();
        try {
            for (int i = 0; i < Constanst.NUM_OF_TOPICS; i++) {
//                Log.d("TayPVS","TayPVS - topic PreferenceUtils - " + PreferenceUtils.getString(getBaseContext(), PreferenceUtils.TOPIC_NUMBER + i));
                JSONObject jsonObject = new JSONObject(PreferenceUtils.getString(getBaseContext(), PreferenceUtils.TOPIC_NUMBER + i));
//                Log.d("TayPVS","TayPVS - topic - jsonObject " + jsonObject.toString());
                Topic topic = JsonParseMachine.parseTopic(jsonObject);
                topics.add(topic);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
