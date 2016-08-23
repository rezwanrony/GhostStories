package com.appracks.GhostStories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.appracks.GhostStories.adapter.Adapter_category;
import com.appracks.GhostStories.adapter.Adapter_title;
import com.appracks.GhostStories.data_object.Title_Ghost_story;
import com.appracks.GhostStories.database.DB_Manager;

import java.util.ArrayList;

public class Story_titles extends AppCompatActivity {
    ListView lv_title;
    String category;
    ArrayList<Title_Ghost_story>title_ghost_stories;
    DB_Manager db_manager;
    Adapter_title adapter_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_titles);
        lv_title=(ListView)findViewById(R.id.lv_title);
        category=getIntent().getStringExtra("category");
        db_manager=DB_Manager.getInstance(this);
        getTitlename();



    }

    private void getTitlename(){
        title_ghost_stories=db_manager.getAllstory(category);
        adapter_title=new Adapter_title(this,title_ghost_stories);
        lv_title.setAdapter(adapter_title);


    }
}
