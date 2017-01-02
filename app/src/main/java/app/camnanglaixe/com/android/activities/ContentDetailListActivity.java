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

import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListContentAdapter;
import app.camnanglaixe.com.android.adapter.ListContentTextAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.SubTopicObject;

/**
 * Created by taypham on 06/12/2016.
 */
public class ContentDetailListActivity extends BaseActivity {

    private ListView contentListview;
    private ListContentAdapter listContentAdapter;
    private SubTopicObject currentSubTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_text);

        init();
    }

    protected void init(){
        setBackBtnOnclick();
        if (getIntent().hasExtra("KEY_CONTENT")) {
            try {
                String json = getIntent().getStringExtra("KEY_CONTENT");
                JSONObject jsonObject = new JSONObject(json);
                currentSubTopic = JsonParseMachine.parseSubTopic(jsonObject);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        ((TextView)findViewById(R.id.title)).setText(currentSubTopic.title);
        contentListview = (ListView)findViewById(R.id.listContentText);
        listContentAdapter = new ListContentAdapter(getBaseContext(), currentSubTopic.content);
        contentListview.setAdapter(listContentAdapter);
        listContentAdapter.notifyDataSetChanged();
        contentListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Gson gson = new Gson();
                String json = gson.toJson(currentSubTopic.content.get(i));
                Log.d("TayPVS", "TayPVS - json : " + json);
                startContentAdvance(json);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
