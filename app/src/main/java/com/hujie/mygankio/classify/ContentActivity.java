package com.hujie.mygankio.classify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.hujie.mygankio.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String url = extras.getString("url");
        //WebView加载数据
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);
    }
}
