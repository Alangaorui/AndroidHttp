package com.grant.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 简易的volley网络请求框架 简单的post请求
 */

public class VolleyActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPassWord;
    private Button mVolleyBtn;
    private String name;
    private String password;
    private RequestQueue mRequestQueue = VolleyApp.queues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initview();
    }

    private void initview() {
        mName = (EditText) findViewById(R.id.name_edit);
        mPassWord = (EditText) findViewById(R.id.password);
        mVolleyBtn = (Button) findViewById(R.id.volley_btn);
        mVolleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mName.getText().toString();
                password = mPassWord.getText().toString();
                volleypost() ;
//                volleyPostTow();//此方法未知错误。
            }

        });
    }

    private void volleypost() {

        StringRequest request = new StringRequest(Request.Method.POST, Constant.USERINFO_USERLOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("aa", "post请求成功" + response);

                Gson gson = new Gson();
                BaseReSelt baseReSelt = gson.fromJson(response, BaseReSelt.class);
                EntityInv invokingResult = baseReSelt.getInvokingResult();
                if (invokingResult.getInvoking().equals("fail")) {
                    return;
                } else {
                    EntityDis disposeResult = baseReSelt.getDisposeResult();
                    Log.i("aa", "post请求成功" + baseReSelt);
                    String userType = disposeResult.getUserType();
                    Log.i("aa", "userType" + userType);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "post请求失败" + volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                /**
                 * 当无参的时候map改成不用传值就可以了
                 */
                HashMap<String, String> map = new HashMap<>();
                map.put("login_phone", name);
                map.put("login_password", password);
                return map;
            }
        };
        mRequestQueue.add(request);
    }


    private void volleyPostTow() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("login_phone", "13476264163");
        map.put("login_password", "q654321");
        //将map转化为JSONObject对象
        JSONObject jsonObject = new JSONObject(map);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                Constant.USERINFO_USERLOGIN,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {//jsonObject为请求返回的Json格式数据
                        Log.e("uid","uid"+jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                     Log.e("volleyError","volleyError"+volleyError);
                    }
                });
        //设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
//        request.setTag("LoginPost");
        //将请求加入全局队列中
        mRequestQueue.add(request);
    }


}
