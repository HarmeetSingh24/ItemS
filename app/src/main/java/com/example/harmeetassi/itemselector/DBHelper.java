package com.example.harmeetassi.itemselector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.TreeMap;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper _instance = null;
    private static final String DATABASE_NAME = "records.db";
    private static final String TABLE_ID = "_id";
    private static final String TABLE_DETAIL = "_detail";
    private static final String TABLE_NAME = "records";
    private static final String DB_CREATE = "create table " + TABLE_NAME + "(" + TABLE_ID + " integer primary key autoincrement, " + TABLE_DETAIL + " text)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new DBHelper(context.getApplicationContext());
        }
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public synchronized  void insert(String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_detail", details);
        db.insert(TABLE_NAME, null, values);
    }

    public synchronized void delete(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, TABLE_ID + "= ?", new String[]{Integer.toString(id)});
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void update(String table, int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TABLE_DETAIL, table);
            db.update(TABLE_NAME, values, TABLE_ID + "= ?", new String[]{Integer.toString(id)});
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public synchronized TreeMap<Integer,String> getAllDetails()
    {
        TreeMap<Integer,String> array_list = new TreeMap<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.put(res.getInt(res.getColumnIndex(TABLE_ID)),res.getString(res.getColumnIndex(TABLE_DETAIL)));
            res.moveToNext();
        }
        return array_list;
    }
}
