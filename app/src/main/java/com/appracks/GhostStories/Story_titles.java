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
import com.appracks.GhostStories.data_object.Ghost;
import com.appracks.GhostStories.database.DB_Manager;

import java.util.ArrayList;

public class Story_titles extends AppCompatActivity {
    ListView lv_title;
    String category;
    ArrayList<Ghost>title_ghost_stories;
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
        title_ghost_stories=db_manager.getAllItem(category);
        adapter_title=new Adapter_title(this,title_ghost_stories);
        lv_title.setAdapter(adapter_title);
        lv_title.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ghost ghost=title_ghost_stories.get(position);
                Intent i=new Intent(Story_titles.this,Full_stories.class);
                i.putExtra("position", position);
                i.putExtra("category", category);
                startActivity(i);
            }
        });


    }
}
