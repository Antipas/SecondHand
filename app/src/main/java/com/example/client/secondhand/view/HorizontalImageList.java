package com.example.client.secondhand.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.client.secondhand.ImageShowActivty;
import com.example.client.secondhand.utils.BitmapUtil;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Lyn on 15/3/10.
 */
public class HorizontalImageList extends HorizontalScrollView implements View.OnClickListener{

    Context context;
    LinearLayout linearLayout;

    public HorizontalImageList(Context context){
        super(context);
        this.context = context;
        createView();
    }

    public HorizontalImageList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView() {
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(linearLayout);
        this.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BitmapUtil.dip2px(context,80)));
    }

    public void addImage(ImageView image){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(BitmapUtil.dip2px(context,80),BitmapUtil.dip2px(context,80));
        params.setMargins(BitmapUtil.dip2px(context,10),0,0,0);
        linearLayout.addView(image,params);
    }

    @Override
    public void onClick(View v) {
        ArrayList imageList = new ArrayList<ImageView>();
        for(int i=0;i<this.getChildCount();i++){
            imageList.add(this.getChildAt(i));
        }

        Intent intent = new Intent(context,ImageShowActivty.class);
        intent.putParcelableArrayListExtra("imageList",imageList);
        context.startActivity(intent);
    }
}
