package app.camnanglaixe.com.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import app.camnanglaixe.com.android.Common.PreferenceUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.Topic;

/**
 * Created by taypham on 05/12/2016.
 */
public class ListSubTopicActivity extends BaseActivity {

    private ListView listViewSTopic;
    private Topic currentTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_topic);
        init();
    }

    private void init(){
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

        listViewSTopic = (ListView) findViewById(R.id.listSubTopic);
        ((TextView)findViewById(R.id.title)).setText(currentTopic.name);
    }
}
