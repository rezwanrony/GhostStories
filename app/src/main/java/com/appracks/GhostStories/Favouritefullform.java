package com.appracks.GhostStories;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appracks.GhostStories.data_object.Ghost;
import com.appracks.GhostStories.database.DB_Manager;

import java.util.ArrayList;

public class Favouritefullform extends AppCompatActivity {

    TextView tv_heading, tv_content;
    String title;
    DB_Manager db_manager;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritefullform);

        tv_heading=(TextView)findViewById(R.id.tv_heading);
        tv_content=(TextView)findViewById(R.id.tv_fullstories_fav);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"vampire.ttf");
        tv_heading.setTypeface(typeface);

        title=getIntent().getStringExtra("title");

        db_manager=new DB_Manager(getApplicationContext());

        content=db_manager.getAllBookmark(title);
        tv_heading.setText(title);
        tv_content.setText(content);
    }
}
