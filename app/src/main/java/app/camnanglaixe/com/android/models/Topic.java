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
    public String type;
    public String icon;
    public String version;
    public List<SubTopicObject> small_topic;

    public Topic(String id, String title, String type, String icon, String version, JSONArray subTitleArray){
        this.id = id;
        this.title = title;
        this.type = type;
        this.icon = icon;
        this.version = version;
        small_topic = new ArrayList<SubTopicObject>();
        try {
            for (int i = 0; i < subTitleArray.length(); i++) {
                String subTopicId = subTitleArray.getJSONObject(i).optString("id");
                String subTopicTitle = subTitleArray.getJSONObject(i).optString("title");
                JSONArray contentArray = subTitleArray.getJSONObject(i).optJSONArray("content");
                SubTopicObject newSubTopic = new SubTopicObject(subTopicId, subTopicTitle, contentArray);
                small_topic.add(newSubTopic);
            }
        }catch (JSONException e){

        }
    }
}
