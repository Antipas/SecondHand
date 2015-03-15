package com.example.client.secondhand;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.client.secondhand.http.AsyncHttpIc;
import com.example.client.secondhand.http.UrlStatic;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.util.ToastUtils;


public class PicDescriptionActivity extends BaseActivity {

    EditText et_title,et_content,et_price;
    TextView tv_kind,tv_xq;
    String [] kindItems ;

    String xqId,kindId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_description);

        initView();
        initData();
    }

    private void initData() {
        AsyncHttpIc.get(UrlStatic.GET_SECOND_TYPE,new AsyncHttpResponseHandler() {
            List<String> list;
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(new String(responseBody));
                    JSONObject obj;
                    list = new ArrayList<>();
                    for(int i=0;i<array.length();i++){
                        obj = array.optJSONObject(i);
                        list.add(obj.getString("name"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                kindItems =  list.toArray(new String[list.size()]);
                list = null;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.show(getApplicationContext(),"网络状况不佳");
            }
        });
    }

    @Override
    protected void initView() {
        et_title = (EditText)findViewById(R.id.et_title);
        et_content = (EditText)findViewById(R.id.et_content);
        et_price = (EditText)findViewById(R.id.et_price);
        tv_kind = (TextView)findViewById(R.id.tv_kind);
        tv_xq = (TextView)findViewById(R.id.tv_xq);

    }

    public void rl_kindsOnClick(View view){
        Dialog kindDialog = new AlertDialog.Builder(PicDescriptionActivity.this)
                .setItems(kindItems,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        kindId = which+1+"";
                        tv_kind.setText("分类：" + kindItems[which]);
                        dialog.dismiss();
                    }
                })
                .create();
        kindDialog.show();

    }

    public void rl_xqOnClick(View view){
        Dialog xqDialog = new AlertDialog.Builder(PicDescriptionActivity.this)
                .setItems(getResources().getStringArray(R.array.xq_name),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        xqId = "0"+which+1;
                        tv_xq.setText("校区：" + getResources().getStringArray(R.array.xq_name)[which]);
                        dialog.dismiss();
                    }
                }).create();
        xqDialog.show();
    }

    public void backOnClick(View view){
        finish();
    }

    public void nextOnClick(View view){
        String title = et_title.getText().toString();
        String content = et_content.getText().toString();
        String price = et_price.getText().toString();
        if(title.isEmpty()){
            ToastUtils.show(getApplicationContext(),"宝贝怎么可以没标题？");
            return;
        }
        if(content.isEmpty()){
            ToastUtils.show(getApplicationContext(),"宝贝的描述呢？");
            return;
        }

        if(xqId.isEmpty()){
            ToastUtils.show(getApplicationContext(),"你所在的校区呢？");
            return;
        }

        if(kindId.isEmpty()){
            ToastUtils.show(getApplicationContext(),"宝贝的分类呢？");
            return;
        }

        if(price.isEmpty()){
            ToastUtils.show(getApplicationContext(),"开个价吧？");
            return;
        }

        ToastUtils.show(getApplicationContext(),xqId+kindId+price+"\n"+title+"\n"+content);
    }


}
