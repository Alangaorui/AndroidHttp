package com.grant.okhttp;

import android.os.Message;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {


    private EditText mName;
    private EditText mPassWord;
    private Button mVolleyBtn;
    private String name;
    private String password;
    private Button mokhttpWeb;
    private OkHttpClient okHttpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initview();
    }

    private void initview() {
        mName = (EditText) findViewById(R.id.name_edit);
        mPassWord = (EditText) findViewById(R.id.password);
        mVolleyBtn = (Button) findViewById(R.id.okhttp_btn);
        mVolleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mName.getText().toString();
                password = mPassWord.getText().toString();

            }

        });

        mokhttpWeb = (Button) findViewById(R.id.okhttp_btn_web);
        mokhttpWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AsynHttpGet();//异步get请求
                AsynPostHttp();//异步post请求
            }
        });
    }
    private void AsynHttpGet() {
        okHttpClient=new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call mcall= okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.e("aa", "aa" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Log.e("aa", "aa" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void AsynPostHttp() {
        okHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url("http://172.161.1.246:90/SMPWeb/signAndBinding/bankList")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * Error:Execution failed for task ':app:transformDexArchiveWithExternalLibsDexMergerForDebug'.
                         > com.android.builder.dexing.DexArchiveMergerException: Unable to merge dex
                           volly和Okhttp导入了相同的包导致，目前还没有解决
                         */
                        try {
                            //自带的json解析
                        JSONArray jsonArray= new JSONObject(str).getJSONArray("disposeResult");
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String bankCode = jsonObject.getString("bankCode");
                            Log.e("bankCode","bankCode"+bankCode);
                        }
                            Log.e("string","string"+jsonArray);
//
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        /**
                         * com.alibaba.fastjson 阿里的json解析
                         */
//                        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
//                        try {
//                            String disposeResult = jsonObject.getString("disposeResult");
//
////                            Log.e("string","string"+disposeResult);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
