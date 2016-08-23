package com.appracks.GhostStories.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appracks.GhostStories.R;
import com.appracks.GhostStories.data_object.Category;
import com.appracks.GhostStories.data_object.Title_Ghost_story;


import java.util.ArrayList;

/**
 * Created by rezwan on 8/7/2016.
 */
public class Adapter_category extends BaseAdapter{
    Context context;
    ArrayList<Category> categoryArrayList;


    public Adapter_category(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview,parent,false);
        }

        TextView tv_category=(TextView)convertView.findViewById(R.id.tv_list);
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"vampire.ttf");
        tv_category.setTypeface(typeface);
        Category m=categoryArrayList.get(position);
        tv_category.setText(m.getCategoryname());
        return convertView;
    }

}
