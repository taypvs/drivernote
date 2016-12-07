package app.camnanglaixe.com.android.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import app.camnanglaixe.com.android.Common.CommonUtils;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.models.ContentDetailRule;

/**
 * Created by taypham on 06/12/2016.
 */
public class ListContentTextAdapter extends BaseAdapter {

    public List<ContentDetailRule> contentDetailRules;
    private LayoutInflater mInflater;
    private Context context;

    public ListContentTextAdapter(Context context, List<ContentDetailRule> contentDetailRules){
        this.context = context;
        this.contentDetailRules = contentDetailRules;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contentDetailRules.size();
    }

    @Override
    public Object getItem(int i) {
        return contentDetailRules.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_list_content_text, null);
            holder.title = (TextView) view.findViewById(R.id.content_title);
            holder.content = (TextView) view.findViewById(R.id.content_txt);
            holder.image = (ImageView) view.findViewById(R.id.content_image);
            holder.titleLayout = (RelativeLayout) view.findViewById(R.id.content_title_layout);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(!contentDetailRules.get(i).title.trim().equals("khong tieu de")) {
            holder.title.setText(contentDetailRules.get(i).title);
            holder.titleLayout.setVisibility(View.VISIBLE);
        }
        else{
            holder.titleLayout.setVisibility(View.GONE);
        }
        holder.content.setText(Html.fromHtml(contentDetailRules.get(i).detail));
        if(contentDetailRules.get(i).image!=null&&!contentDetailRules.get(i).image.equals("")) {
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setImageDrawable(CommonUtils.getDrawableResourceByName(context, contentDetailRules.get(i).image));
        }
        return view;
    }

    class ViewHolder {
        TextView title;
        TextView content;
        ImageView image;
        RelativeLayout titleLayout;
    }
}