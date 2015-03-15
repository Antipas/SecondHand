package com.example.client.secondhand;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.client.secondhand.adapter.ImageViewPagerAdapter;
import com.example.client.secondhand.application.SHApplication;
import com.example.client.secondhand.utils.BitmapUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 照片Viewpager
 * 传入ArrayList<Image>
 * 传入currentImage 位置
 */
public class ImageShowActivty extends BaseActivity {
    ViewPager imageViewPager;
    TextView curTV,allTV;
    ArrayList imageList;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show_activty);
        initView();
        initList();
        initData();

    }

    private void initData() {
        curTV.setText(getIntent().getIntExtra("position",1) + 1 + "");
        allTV.setText("/"+imageList.size());
        imageViewPager.setAdapter(new ImageViewPagerAdapter(imageList));
        imageViewPager.setCurrentItem(getIntent().getIntExtra("position",1));
        imageViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curTV.setText(position+1+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initList() {
        imageList = new ArrayList<>();
        imageList = getIntent().getParcelableArrayListExtra("imageList");

        if(imageList.isEmpty()){
            return;
        }

        if(imageList.get(0) instanceof Uri)
        {
            ArrayList imageViewArrayList = new ArrayList();
            for(Object obj:imageList){

                try {
                    bitmap = BitmapUtil.getImageFromUri(getApplicationContext(), (Uri)obj, SHApplication.getScreenHW(getApplicationContext())[0]);
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageBitmap(bitmap);
                    imageViewArrayList.add(imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            imageList = imageViewArrayList;
            imageViewArrayList = null;
        }
    }


    @Override
    protected void initView() {
        imageViewPager = (ViewPager)findViewById(R.id.vp_image);
        curTV = (TextView)findViewById(R.id.tv_cur_num);
        allTV = (TextView)findViewById(R.id.tv_all_num);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
