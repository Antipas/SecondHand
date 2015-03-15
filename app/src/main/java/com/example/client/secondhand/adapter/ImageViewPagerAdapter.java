package com.example.client.secondhand.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Lyn on 15/3/10.
 */
public class ImageViewPagerAdapter extends PagerAdapter {
    ArrayList<ImageView> imageList;

    public ImageViewPagerAdapter(ArrayList list){
        imageList = new ArrayList<>();
        imageList = list;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageList.get(position), 0);//添加页卡
        return imageList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageList.get(position));//删除页卡

    }
}
