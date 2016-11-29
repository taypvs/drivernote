package app.camnanglaixe.com.android.models;

import java.util.List;

/**
 * Created by taypham on 29/11/2016.
 */
public class SubTopicObject {
    public String id;
    public String title;
    public List<ContentDetailRule> detailRules;

    public SubTopicObject(String id, String title){
        this.id = id;
        this.title = title;
    }


}
