package app.camnanglaixe.com.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListContentTextAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.SubTopicObject;

/**
 * Created by taypham on 06/12/2016.
 */
public class ContentDetailTextActivity extends BaseActivity {

    private ListView contentListview;
    private ListContentTextAdapter listContentTextAdapter;
    private SubTopicObject currentSubTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_text);

        init();
    }

    protected void init(){
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

        ((TextView)findViewById(R.id.title)).setText(currentSubTopic.title);
        contentListview = (ListView)findViewById(R.id.listContentText);
        listContentTextAdapter = new ListContentTextAdapter(getBaseContext(), currentSubTopic.content);
        contentListview.setAdapter(listContentTextAdapter);
        listContentTextAdapter.notifyDataSetChanged();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
