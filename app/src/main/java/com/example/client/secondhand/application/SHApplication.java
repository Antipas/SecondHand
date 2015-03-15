package com.example.client.secondhand.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.client.secondhand.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyn on 15/3/9.
 */
public class SHApplication extends Application {

    public static ImageLoader imageLoader;
    static DisplayImageOptions options;
//    public static int []screenWH = new int[2];
    public static List<Activity> activityArrayList = new ArrayList<Activity>();;
    @Override
    public void onCreate() {
        super.onCreate();
//        screenWH = getScreenHW(getApplicationContext());


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(5)
                .memoryCache(new WeakMemoryCache())
                .build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static void downloadCornImage(String url,ImageView imageView){
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.blank)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .showImageOnFail(R.drawable.blank)
                .displayer(new RoundedBitmapDisplayer(180))
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(url,imageView,options);
    }

    public static void downloadIamge(String url,ImageView imageView){
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.blank)
                .showImageForEmptyUri(R.drawable.blank)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .showImageOnFail(R.drawable.blank)
                .build();

        imageLoader.displayImage(url,imageView,options);
    }

    public static void downloadIamge(String url,ImageView imageView, ImageLoadingListener imageLoadingListener){
        options = new DisplayImageOptions.Builder()
                //.showImageOnLoading(R.drawable.blank)
                .showImageForEmptyUri(R.drawable.blank)
                .showImageOnFail(R.drawable.blank)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();

        imageLoader.displayImage(url,imageView,options, imageLoadingListener);
    }

    public static void clearCache(){
        imageLoader.clearDiskCache();
        imageLoader.clearMemoryCache();
    }

    public static int[] getScreenHW(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        return new int[]{width,height};
    }
}
