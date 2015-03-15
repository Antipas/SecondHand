package com.example.client.secondhand.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.client.secondhand.R;
import com.example.client.secondhand.SelectImageActivity;
import com.example.client.secondhand.utils.BitmapUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lyn on 15/3/11.
 */
public class CameraGridViewAdapter extends BaseAdapter{
    Context context;
    ArrayList list;
    public CameraGridViewAdapter(Context context,ArrayList list ){
        this.context = context;
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.gridview_item, null);
            holder = new ViewHolder();
            holder.Imageitem = (ImageView) view.findViewById(R.id.image_item);
            view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }

        try {
            holder.Imageitem.setImageBitmap(BitmapUtil.getImageFromUri(context,(Uri)list.get(position),BitmapUtil.dip2px(context,60)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private static class ViewHolder{
        ImageView Imageitem;

    }
}
