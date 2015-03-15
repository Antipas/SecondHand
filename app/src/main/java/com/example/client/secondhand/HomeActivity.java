package com.example.client.secondhand;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.client.secondhand.fragment.HomeFragment;
import com.example.client.secondhand.fragment.PersonFragment;


public class HomeActivity extends BaseActivity implements PersonFragment.OnPersonFragmentListener,HomeFragment.OnHomeFragmentListener{

    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();


    }

    @Override
    protected void initView() {


    }

    public void homeOnClick(View view){
//        replaceFragment(HomeFragment.newInstance("1","2"));
    }

    public void addOnClick(View view){
        Intent intent = new Intent(HomeActivity.this,SelectImageActivity.class);
        startActivity(intent);

    }

    public void personOnClick(View view){
        replaceFragment( PersonFragment.newInstance("1", "2"));
    }

    private void replaceFragment(Fragment newFragment) {

        FragmentTransaction trasection =
                getFragmentManager().beginTransaction();
        if(!newFragment.isAdded()){
            try{
                //FragmentTransaction trasection =
                getFragmentManager().beginTransaction();
                trasection.replace(R.id.fl_container, newFragment);
                trasection.addToBackStack(null);
                trasection.commit();

            }catch (Exception e) {
                e.printStackTrace();
            }
        }else
            trasection.show(newFragment);
    }


    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }

    @Override
    public void OnPersonFragment(Uri uri) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;//更新firstTime
                return true;
            } else {                                                    //两次按键小于2秒时，退出应用
                System.exit(0);
            }
        }
        return false;
    }
}
