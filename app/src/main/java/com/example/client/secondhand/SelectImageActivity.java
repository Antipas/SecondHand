package com.example.client.secondhand;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.client.secondhand.adapter.CameraGridViewAdapter;
import com.example.client.secondhand.utils.LogUtil;

import java.util.ArrayList;

import cn.trinea.android.common.util.ToastUtils;


public class SelectImageActivity extends BaseActivity {

    private static final int REQUEST_PICK_IMAGE = 1;
    public static final int SELECT_PIC_BY_TACK_PHOTO = 5;
    private static final int MAX_PIC_NUM = 10;

    FrameLayout fl_camera;
    GridView imageGridView;
    private Uri photoUri;
    private ArrayList<Uri> uriArrayList;
    private CameraGridViewAdapter cameraGridViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        initView();
        uriArrayList = new ArrayList<>();
        cameraGridViewAdapter = new CameraGridViewAdapter(getApplicationContext(),uriArrayList);

        setData();
        showCameraView();
    }

    private void setData() {
        imageGridView.setAdapter(cameraGridViewAdapter);
        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(SelectImageActivity.this, ImageShowActivty.class);
                    intent.putExtra("position", position);
                    intent.putParcelableArrayListExtra("imageList", uriArrayList);
                    startActivity(intent);

            }
        });
    }

    @Override
    protected void initView() {
        fl_camera = (FrameLayout)findViewById(R.id.fl_camera);
        imageGridView = (GridView)findViewById(R.id.gd_selected_image);
    }

    private void showCameraView(){
        fl_camera.setVisibility(View.VISIBLE);
    }

    private void hideCameraView(){
        fl_camera.setVisibility(View.GONE);
    }

    public void flCameraOnClick(View view){
        if(cameraGridViewAdapter.getCount() < MAX_PIC_NUM) {
            Dialog alertDialog = new AlertDialog.Builder(SelectImageActivity.this)

                    .setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                photoPickerIntent.setType("image/*");
                                startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);
                            } else if (which == 1) {
                                takePhoto();
                            }

                        }
                    }).create();
            alertDialog.show();

        }else{
            final Dialog maxDialog = new AlertDialog.Builder(SelectImageActivity.this)
                    .setMessage("最多上传10张图片")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            maxDialog.show();
        }

    }

    public void next(View view){
        if(cameraGridViewAdapter.getCount() == 0){
            ToastUtils.show(getApplicationContext(),"还没有拍照片哦");
            return;
        }

        Intent intent = new Intent(SelectImageActivity.this,PicDescriptionActivity.class);
        startActivity(intent);

    }

    /**
     * 拍照获取图片
     */
    public void takePhoto() {
        //执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if(SDState.equals(Environment.MEDIA_MOUNTED))
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE"
            ContentValues values = new ContentValues();
            photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        }else{
            Toast.makeText(this,"内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        switch (requestCode) {
            case REQUEST_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
//                    CuteApplication.tempUri = data.getData();
//                    startActivityForResult(cropIntent, REQUEST_PICK_IMAGE);
                    //加入girdview
                    uriArrayList.add(data.getData());
                    updateGridView();
                } else {
                    finish();
                }
                break;
            case SELECT_PIC_BY_TACK_PHOTO:
                LogUtil.v("resultCode", resultCode);
                if(resultCode == 0)
                    finish();
                else {
                    //加入girdview
                    uriArrayList.add(photoUri);
                    updateGridView();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    /**
     *  更新GridView Adapter
     *  会多次调用
     */
    private void updateGridView(){
        if(cameraGridViewAdapter.getCount() > 0){
            hideCameraView();
            imageGridView.setVisibility(View.VISIBLE);
        }else{
            showCameraView();
            imageGridView.setVisibility(View.INVISIBLE);
        }

        LogUtil.v("uriList",uriArrayList.get(uriArrayList.size()-1).toString());
        cameraGridViewAdapter.notifyDataSetChanged();
    }

}
