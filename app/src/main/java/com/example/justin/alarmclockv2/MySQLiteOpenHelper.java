package com.example.justin.alarmclockv2;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
/**
 * Created by Justin on 1/18/16.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{


    public MySQLiteOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table messageTable (" +
                "_id integer primary key autoincrement, " +
                "content text, " +
                "time text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        String sql="drop table if exists messages";
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor readEntry(SQLiteDatabase db){
        String[] allColumns = new String[] {"_id","content","time"};
        Cursor c = db.query("messageTable",allColumns,null,null,null,null,null);
        if(c!=null)
            c.moveToFirst();
        return c;
    }

    public void removeAllEntries(SQLiteDatabase db){
        
    }

}
