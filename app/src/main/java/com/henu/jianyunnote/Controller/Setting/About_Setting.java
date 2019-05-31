package com.henu.jianyunnote.Controller.Setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.henu.jianyunnote.R;
import com.henu.jianyunnote.Util.AtyUtil;

public class About_Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about__setting );
        AtyUtil.getInstance().addActivity(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        WebView webView = findViewById( R.id.wv );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.setWebViewClient( new WebViewClient() );
        webView.loadUrl( "https://github.com/yidongyingyongjishukaifa/JianYunNote" );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                return true;
        }
    }
}
