package com.example.client.secondhand;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.client.secondhand.application.SHApplication;

/**
 * Created by client on 15/3/9.
 */
public abstract class BaseActivity  extends Activity{

    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SHApplication.activityArrayList.add(this);
        super.onCreate(savedInstanceState);
    }


}
