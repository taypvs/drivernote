package app.camnanglaixe.com.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListContentDefAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.SubTopicObject;

/**
 * Created by taypham on 08/12/2016.
 */
public class ContentDetailDefActivity extends BaseActivity {

    private ListView listContentView;
    private ListContentDefAdapter listContentDefAdapter;
    private SubTopicObject currentSubTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_def);
    }

    private void init(){
        if (getIntent().hasExtra("KEY_CONTENT")) {
            try {
                String json = getIntent().getStringExtra("KEY_CONTENT");
                JSONObject jsonObject = new JSONObject(json);
                Log.d("TayPVS", "TayPVS - subtopic - jsonObject " + jsonObject.toString());
                currentSubTopic = JsonParseMachine.parseSubTopic(jsonObject);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        listContentView = (ListView) findViewById(R.id.listContentText);
        listContentDefAdapter = new ListContentDefAdapter(getBaseContext(), currentSubTopic.content);
    }

}
