package app.camnanglaixe.com.android.models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taypham on 29/11/2016.
 */
public class Topic {
    public String id;
    public String title;
    public List<SubTopicObject> subTopics;

    public Topic(String id, String title, JSONArray subTitleArray){
        this.id = id;
        this.title = title;
        subTopics = new ArrayList<SubTopicObject>();
        try {
            for (int i = 0; i < subTitleArray.length(); i++) {
                String subTopicId = subTitleArray.getJSONObject(i).optString("id");
                String subTopicTitle = subTitleArray.getJSONObject(i).optString("title");
                JSONArray contentArray = subTitleArray.getJSONArray(i);
                SubTopicObject newSubTopic;

            }
        }catch (JSONException e){

        }

    }
}
