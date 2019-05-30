package com.henu.jianyunnote.Controller.Setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.henu.jianyunnote.R;
import com.henu.jianyunnote.Util.AtyUtil;

public class SettingController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setting );
        // 添加Activity到堆栈
        AtyUtil.getInstance().addActivity(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        TextView about = findViewById( R.id.info );
        about.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(SettingController.this, About_Setting.class);
                startActivity( intent );
            }
        } );
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
