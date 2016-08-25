package com.appracks.GhostStories.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appracks.GhostStories.R;
import com.appracks.GhostStories.Story_titles;
import com.appracks.GhostStories.data_object.Ghost;

import java.util.ArrayList;

/**
 * Created by rezwan on 8/23/2016.
 */
public class Adapter_title extends BaseAdapter {

    Context context;
    ArrayList<Ghost> title_ghost_stories;

    public Adapter_title(Context context, ArrayList<Ghost> title_ghost_stories) {
        this.context = context;
        this.title_ghost_stories = title_ghost_stories;
    }

    @Override
    public int getCount() {
        return title_ghost_stories.size();
    }

    @Override
    public Object getItem(int position) {
        return title_ghost_stories.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview_title,parent,false);
        }

        TextView tv_title=(TextView)convertView.findViewById(R.id.tv_title);
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"vampire.ttf");
        tv_title.setTypeface(typeface);
        Ghost title_ghost_story= title_ghost_stories.get(position);
        tv_title.setText(title_ghost_story.getTitle());
        return convertView;
    }
    }

