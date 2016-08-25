package com.appracks.GhostStories;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appracks.GhostStories.adapter.Adapter_category;
import com.appracks.GhostStories.data_object.Category;

import java.util.ArrayList;

public class All_stories extends AppCompatActivity {

    ListView categorylistview;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stories);
        category=(TextView)findViewById(R.id.tv_cat);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"vampire.ttf");
        category.setTypeface(typeface);
        categorylistview=(ListView)findViewById(R.id.lv_category);
        final String[]category={"Artifacts and Objects","Beings and Entities","Dreams and Madness","Locations and Sites","Murders and Deaths","Rites and Rituals","Strange and Unknown"};

        final ArrayList<Category> categoryArrayList=new ArrayList<Category>();
        for (int i = 0; i < 7; i++) {
            categoryArrayList.add(new Category(category[i]));
        }
        Adapter_category adapter_category=new Adapter_category(getApplicationContext(),categoryArrayList);
        categorylistview.setAdapter(adapter_category);
        adapter_category.notifyDataSetChanged();
      categorylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent= new Intent(All_stories.this,Story_titles.class);
              intent.putExtra("category",category[position]);
              startActivity(intent);

          }
      });


    }
}
