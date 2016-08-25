package com.appracks.GhostStories;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appracks.GhostStories.adapter.Adapter_title;
import com.appracks.GhostStories.data_object.Ghost;
import com.appracks.GhostStories.database.DB_Manager;

import java.util.ArrayList;

public class Favourite_stories extends AppCompatActivity {
    ListView lv_title;
    String category;
    ArrayList<Ghost>title_ghost_stories;
    DB_Manager db_manager;
    Adapter_title adapter_title;
    TextView tv_favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_stories);
        category=getIntent().getStringExtra("category");
        lv_title=(ListView)findViewById(R.id.lv_fav);
        tv_favourite=(TextView)findViewById(R.id.tv_favstories);
        Typeface typeface=Typeface.createFromAsset(getApplicationContext().getAssets(),"vampire.ttf");
        tv_favourite.setTypeface(typeface);
        db_manager=DB_Manager.getInstance(this);
        getAllFavouriteItem();

    }

    private void getAllFavouriteItem(){

         final ArrayList<Ghost> ghostArrayList=db_manager.getAllItemBookmarked();
        adapter_title=new Adapter_title(getApplicationContext(),ghostArrayList);
        lv_title.setAdapter(adapter_title);
        lv_title.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title= (String) lv_title.getItemAtPosition(position);
                Intent intent=new Intent(Favourite_stories.this, Favouritefullform.class);
                intent.putExtra("title",title);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);


            }
        });

    }
}
