package com.example.client.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.client.secondhand.R;

import org.json.JSONArray;

/**
 * Created by Lyn on 15/3/10.
 */
public class HomeListAdapter extends BaseAdapter{
    private JSONArray kindArray;
    private Context ctx;


    public HomeListAdapter(JSONArray kindArray
            , Context ctx) {
        this.kindArray = kindArray;
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return kindArray.length();
    }

    @Override
    public Object getItem(int position) {
        return kindArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder ;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater flater = LayoutInflater.from(ctx);
            convertView = flater.inflate(R.layout.home_listview_item, parent,false);
//            holder.userIcon = (RoundedImageView)convertView.findViewById(R.id.user_icon);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder{

    }
}
