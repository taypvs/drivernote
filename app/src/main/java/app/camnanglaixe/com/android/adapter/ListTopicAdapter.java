package app.camnanglaixe.com.android.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import app.camnanglaixe.com.android.models.Topic;

/**
 * Created by taypham on 02/12/2016.
 */
public class ListTopicAdapter extends BaseAdapter {

    private List<Topic> topics;

    public ListTopicAdapter(List<Topic> topics){
        this.topics = topics;
    }

    @Override
    public int getCount() {
        return topics.size();
    }

    @Override
    public Object getItem(int i) {
        return topics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
