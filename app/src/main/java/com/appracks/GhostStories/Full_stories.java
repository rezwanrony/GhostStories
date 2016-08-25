package com.appracks.GhostStories;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appracks.GhostStories.data_object.Ghost;
import com.appracks.GhostStories.database.DB_Manager;

import java.util.ArrayList;

public class Full_stories extends AppCompatActivity {
    TextView tv_heading,fullstory;
    LinearLayout lay_previous, lay_copy, lay_favourite, lay_share, lay_next, rlayout;
    ImageView imageView;
    String category;
    DB_Manager dbManager;
    ArrayList<Ghost>dataList;
    int data_index;
    private String totalfull_form="";
    private int data_id=0;
    Ghost ghost;
    String title,from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_stories);
        tv_heading=(TextView)findViewById(R.id.tv_head);
        fullstory=(TextView)findViewById(R.id.tv_fullstories);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"vampire.ttf");
        tv_heading.setTypeface(typeface);
        lay_previous = (LinearLayout) findViewById(R.id.lay_previous);
        lay_copy = (LinearLayout) findViewById(R.id.lay_copy);
        lay_favourite = (LinearLayout) findViewById(R.id.lay_favourite);
        lay_share = (LinearLayout) findViewById(R.id.lay_share);
        lay_next = (LinearLayout) findViewById(R.id.lay_next);
        imageView=(ImageView)findViewById(R.id.btn_favourite);
        category = getIntent().getStringExtra("category");
        title=getIntent().getStringExtra("title");
        from=getIntent().getStringExtra("from");
        dbManager = DB_Manager.getInstance(this);
        dataList = dbManager.getAllItem(category);
        rlayout = (LinearLayout) findViewById(R.id.rlayout);
        data_index = getIntent().getIntExtra("position",0);
        lay_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goLeft()) {
                    Toast.makeText(getApplicationContext(), "List is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Ghost ghost = dataList.get(data_index);
        tv_heading.setText(ghost.getTitle());
        fullstory.setText(ghost.getContent());


        lay_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (goRight()) {
                    Toast.makeText(getApplicationContext(), "List is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Ghost ghost1 = dataList.get(data_index);
        tv_heading.setText(ghost1.getTitle());
        fullstory.setText(ghost1.getContent());

        lay_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 0) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,totalfull_form );
                    intent.setType("text/plain");
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "List is empty" + "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lay_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 0) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("clipdata", fullstory.getText().toString() + "\n" );
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Text Copied to clipboard", Toast.LENGTH_SHORT).show();
                } else {
                    fullstory.setText("List empty");
                }
            }
        });


        lay_favourite.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Ghost ghost= dataList.get(data_index);

                if (dbManager.isBookmarked(String.valueOf(ghost.getId()))) {

                    if (dbManager.changeBookmarked(String.valueOf(ghost.getId()), 0)) {

                        Toast.makeText(getApplicationContext(), "Removed from Bookmarked" + "", Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.icon_favourite_n);

                    }

                } else {

                    if (dbManager.changeBookmarked(String.valueOf(ghost.getId()), 1)) {
                        Toast.makeText(getApplicationContext(), "Added to Bookmark" + "", Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.icon_favourite_selected);
                    }
                }
            }
        });
    }

    public boolean goLeft() {
        if (dataList.size() > 0) {
            if (data_index == 0) {
                data_index = dataList.size() - 1;
            } else {
                data_index--;
            }

           Ghost ghost = dataList.get(data_index);
            tv_heading.setText(ghost.getTitle());
            fullstory.setText(ghost.getContent());
            rlayout.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in));
            data_id = ghost.getId();
            tv_heading.setText(ghost.getTitle());
            fullstory.setText(ghost.getContent());

            return false;
        } else {
            return true;
        }
    }

    public boolean goRight() {
        if (dataList.size() > 0) {
            if ((data_index + 1) >= dataList.size()) {
                data_index = 0;
            } else {
                data_index++;
            }
            Ghost ghost= dataList.get(data_index);
            tv_heading.setText(ghost.getTitle());
            fullstory.setText(ghost.getContent());
            rlayout.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in));
            data_id = ghost.getId();
            tv_heading.setText(ghost.getTitle());
            fullstory.setText(ghost.getContent());

            return false;
        } else {
            return true;
        }
    }


}
