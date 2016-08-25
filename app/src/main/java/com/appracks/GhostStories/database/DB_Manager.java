package com.appracks.GhostStories.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appracks.GhostStories.data_object.Ghost;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DB_Manager extends SQLiteOpenHelper {
    private static DB_Manager managerInstance;
    private static final String DB_NAME = "ghost_db";
    private static String DB_PATH;
    private SQLiteDatabase database;
    private Context context;
    Cursor cursor;

    private static final String DATA_TABLE = "Ghost";
    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String CONTENT_FIELD = "content";
    private static final String CATEGORY_FIELD = "category";
    private static final String FAVOURITE_FIELD = "isfavourite";


    /*public ArrayList<Title_Ghost_story> getAllstory(String category){

        ArrayList<Title_Ghost_story> ghost_storyArrayList=new ArrayList<Title_Ghost_story>();
        cursor=this.database.rawQuery("select title from Ghost where category='"+category+"'",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                int id=cursor.getInt(0);
                String title=cursor.getString(cursor.getColumnIndex(TITLE_FIELD));
                Title_Ghost_story title_ghost_story=new Title_Ghost_story(title,id);
                ghost_storyArrayList.add(title_ghost_story);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return ghost_storyArrayList;
    }*/

    /*public ArrayList<Ghost> getAllstory(String category){

        ArrayList<Ghost> ghost_storyArrayList=new ArrayList<Ghost>();
        Cursor cursor = this.database.query(DATA_TABLE, null, "category=?", new String[]{category}, null, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                int id=cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(TITLE_FIELD));
                String content=cursor.getString(cursor.getColumnIndex(CONTENT_FIELD));
                String cat=cursor.getString(cursor.getColumnIndex(CATEGORY_FIELD));
                Ghost title_ghost_story=new Ghost(id,title,content,cat);
                ghost_storyArrayList.add(title_ghost_story);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return ghost_storyArrayList;
    }*/

    public ArrayList<Ghost> getAllItem(String cat) {
        ArrayList<Ghost> ghostArrayList = new ArrayList<Ghost>();
        Cursor cursor = this.database.query(DATA_TABLE, null, "category=?", new String[]{cat}, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String title=cursor.getString(cursor.getColumnIndex(TITLE_FIELD));
                int id=cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                String content=cursor.getString(cursor.getColumnIndex(CONTENT_FIELD));
                String category=cursor.getString(cursor.getColumnIndex(CATEGORY_FIELD));
                Ghost title_ghost_story=new Ghost(id,title,content,category);
                ghostArrayList.add(title_ghost_story);


                cursor.moveToNext();
            }
            cursor.close();
        }
        return ghostArrayList;
    }

    public ArrayList<Ghost> getAllItemBookmarked() {
        ArrayList<Ghost> ghostArrayList = new ArrayList<Ghost>();
        Cursor cursor = this.database.query(DATA_TABLE, null,  FAVOURITE_FIELD + "=?", new String[]{ String.valueOf(1)}, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id=cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(TITLE_FIELD));
                String content=cursor.getString(cursor.getColumnIndex(CONTENT_FIELD));
                String category=cursor.getString(cursor.getColumnIndex(CATEGORY_FIELD));

                Ghost ghost = new Ghost(id, title, content, category);
                ghostArrayList.add(ghost);


                cursor.moveToNext();
            }

        }
        cursor.close();
        return ghostArrayList;
    }

    public String getAllBookmark(String title) {
        String content=null;
        Cursor cursor = this.database.query(DATA_TABLE, new String[]{CONTENT_FIELD},  TITLE_FIELD + "=?", new String[]{ title}, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                content=cursor.getString(cursor.getColumnIndex(CONTENT_FIELD));


                cursor.moveToNext();
            }

        }
        cursor.close();
        return content;
    }

    public boolean isBookmarked(String id) {
        Cursor cursor = this.database.query(DATA_TABLE, new String[]{FAVOURITE_FIELD}, ID_FIELD + "=? ", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            if (cursor.getString(cursor.getColumnIndex(FAVOURITE_FIELD)).equals("1")) {
                return true;
            }
        }
        return false;
    }

    public boolean changeBookmarked(String id, int value) {
        ContentValues values = new ContentValues();
        values.put(FAVOURITE_FIELD, value);
        if (this.database.update(DATA_TABLE, values, ID_FIELD + "=?", new String[]{id}) > 0) {
            return true;
        }
        return false;
    }







    public DB_Manager(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.database = openDatabase();
    }

    public static DB_Manager getInstance(Context context) {
        if (managerInstance == null) {
            managerInstance = new DB_Manager(context);
        }
        return managerInstance;
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public SQLiteDatabase openDatabase() {
        String path = DB_PATH + DB_NAME;
        if (database == null) {
            createDatabase();
            database = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
        return database;
    }

    private void createDatabase() {

        String path = DB_PATH + DB_NAME;
        File file = new File(path);
        if (file.exists()) {
        } else {
            this.getReadableDatabase();
            copyDatabase();
        }
    }

    private void copyDatabase() {
        try {
            InputStream dbInputStream = context.getAssets().open(DB_NAME);
            String path = DB_PATH + DB_NAME;
            OutputStream dbOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[4096];
            int readCount = 0;
            while ((readCount = dbInputStream.read(buffer)) > 0) {
                dbOutputStream.write(buffer, 0, readCount);
            }
            dbInputStream.close();
            dbOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
