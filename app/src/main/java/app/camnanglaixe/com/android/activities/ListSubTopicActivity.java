package app.camnanglaixe.com.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListSubTopicAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.Topic;

/**
 * Created by taypham on 05/12/2016.
 */
public class ListSubTopicActivity extends BaseActivity {

    private ListView listViewSTopic;
    private Topic currentTopic;
    private ListSubTopicAdapter listSubTopicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_topic);
        init();
    }

    protected void init(){
        setBackBtnOnclick();
        if (getIntent().hasExtra("KEY_TOPIC")) {
            try {
                int i = getIntent().getIntExtra("KEY_TOPIC", 0);
                JSONObject jsonObject = new JSONObject(PreferenceUtils.getString(getBaseContext(), PreferenceUtils.TOPIC_NUMBER + i));
                Log.d("TayPVS", "TayPVS - subtopic - jsonObject " + jsonObject.toString());
                currentTopic = JsonParseMachine.parseTopic(jsonObject);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        ((TextView)findViewById(R.id.title)).setText(currentTopic.name);

        listViewSTopic = (ListView) findViewById(R.id.listSubTopic);
        listSubTopicAdapter = new ListSubTopicAdapter(getBaseContext(), currentTopic.small_topic);
        listViewSTopic.setAdapter(listSubTopicAdapter);
        listViewSTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Convert topic to String and save
                Gson gson = new Gson();
                String json = gson.toJson(currentTopic.small_topic.get(i));
                startContentActivity(currentTopic.small_topic.get(i).type_name, json);
//                overridePendingTransition(R.anim.slide_from_right, 0);
            }
        });
    }
}
