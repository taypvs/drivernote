package app.camnanglaixe.com.android.models;

import org.json.simple.JSONObject;

/**
 * Created by taypham on 29/11/2016.
 */
public class ContentDetailRule {
    public String id;
    public String type;
    public String title;
    public String detail;

    public ContentDetailRule(JSONObject jsonObject){
        id = (String)jsonObject.get("id");
        type = (String)jsonObject.get("type");
        title = (String)jsonObject.get("title");
        detail = (String)jsonObject.get("detail");
    }

    public ContentDetailRule(String id, String type, String title, String detail){
        this.id = id;
        this.type = type;
        this.title = title;
        this.detail = detail;
    }

}
