package com.appracks.GhostStories;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  LinearLayout lay_allstories,lay_favourite,lay_videos,lay_about,lay_rate;
    TextView tv_allstories,tv_favourite,tv_videos,tv_about,tv_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lay_allstories=(LinearLayout)findViewById(R.id.lay_allstories);
        lay_favourite=(LinearLayout)findViewById(R.id.lay_favouritestories);
        lay_videos=(LinearLayout)findViewById(R.id.lay_videos);
        lay_rate=(LinearLayout)findViewById(R.id.lay_rate);
        lay_about=(LinearLayout)findViewById(R.id.lay_about);

        tv_allstories=(TextView)findViewById(R.id.tv_allstories);
        tv_favourite=(TextView)findViewById(R.id.tv_favstories);
        tv_videos=(TextView)findViewById(R.id.tv_horrorvideos);
        tv_rate=(TextView)findViewById(R.id.tv_rate);
        tv_about=(TextView)findViewById(R.id.tv_about);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"vampire.ttf");
        tv_allstories.setTypeface(typeface);
        tv_favourite.setTypeface(typeface);
        tv_videos.setTypeface(typeface);
        tv_rate.setTypeface(typeface);
        tv_about.setTypeface(typeface);

        lay_allstories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,All_stories.class);
                startActivity(intent);
            }
        });

        lay_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Favourite_stories.class);
                startActivity(intent);
            }
        });

        lay_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Videos.class);
                startActivity(intent);
            }
        });

        lay_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Rate_us.class);
                startActivity(intent);
            }
        });

        lay_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,About_us.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Are you sure to want to quit?");
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                System.exit(0);
            }
        });
        alertDialog.setNegativeButton("No", null);
        alertDialog.show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
