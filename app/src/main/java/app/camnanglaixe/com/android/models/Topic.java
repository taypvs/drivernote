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
    public int version;
    public List<SubTopicObject> subTopics;

    public Topic(String id, String title, String type, String icon, int version, JSONArray subTitleArray){
        this.id = id;
        this.title = title;
        this.type = type;
        this.icon = icon;
        this.version = version;
        subTopics = new ArrayList<SubTopicObject>();
        try {
            for (int i = 0; i < subTitleArray.length(); i++) {
                String subTopicId = subTitleArray.getJSONObject(i).optString("id");
                String subTopicTitle = subTitleArray.getJSONObject(i).optString("title");
                JSONArray contentArray = subTitleArray.getJSONObject(i).optJSONArray("content");
                SubTopicObject newSubTopic = new SubTopicObject(subTopicId, subTopicTitle, contentArray);
                subTopics.add(newSubTopic);
            }
        }catch (JSONException e){

        }
    }
}
