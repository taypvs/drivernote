package app.camnanglaixe.com.android.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.adapter.ListContentSignAdapter;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.SubTopicObject;

/**
 * Created by taypham on 06/12/2016.
 */
public class ListSignActivity extends BaseActivity {

    private SubTopicObject currentSubTopic;
    private ListView listSignView;
    private ListContentSignAdapter listContentSignAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_sign);

        init();
    }

    protected void init() {
        setBackBtnOnclick();
        if (getIntent().hasExtra("KEY_CONTENT")) {
            try {
                String json = getIntent().getStringExtra("KEY_CONTENT");
                JSONObject jsonObject = new JSONObject(json);
                Log.d("TayPVS", "TayPVS - subtopic - jsonObject " + jsonObject.toString());
                currentSubTopic = JsonParseMachine.parseSubTopic(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ((TextView) findViewById(R.id.title)).setText(currentSubTopic.title);
        listSignView = (ListView) findViewById(R.id.listContentSign);
        listContentSignAdapter = new ListContentSignAdapter(this, currentSubTopic.content);
        listSignView.setAdapter(listContentSignAdapter);

    }

    public void showDialogContent(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListSignActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(title);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //DO TASK
                arg0.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        //After calling show method, you need to check your condition and
        //enable/ disable buttons of dialog
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false); //BUTTON1 is positive button
    }
}