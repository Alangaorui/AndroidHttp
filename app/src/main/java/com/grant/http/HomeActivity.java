package com.grant.http;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.grant.volley.VolleyActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity:";
    private TextView mVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.volley:
                Log.e(TAG,"volley"+"volley");
                Intent volleyintent = new Intent(HomeActivity.this,VolleyActivity.class);
                startActivity(volleyintent);
                break;
        }
    }
}
