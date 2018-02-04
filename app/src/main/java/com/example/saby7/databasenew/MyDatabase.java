package com.example.saby7.databasenew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saby7 on 04-07-2017.
 */

class MyDatabase {
    MyHelper mh;
    SQLiteDatabase sdb;

    public MyDatabase(Context c){
        mh = new MyHelper(c,"saby",null,1);
    }

    public void open(){
        sdb = mh.getWritableDatabase();
    }


    public void insertdata(String ename, String esalary,String edesignation){
        ContentValues cv = new ContentValues();
        cv.put("ename",ename);
        cv.put("esalary",esalary);
        cv.put("edesignation",edesignation);
        sdb.insert("emoplyee",null,cv);

    }

    public Cursor readdata(){
        Cursor c = null;
        c = sdb.query("emoplyee",null,null,null,null,null,null);
        return c;
    }


    public void updatedata(int pos,String ename, String esalary,String edesignation){
        ContentValues cv = new ContentValues();
        cv.put("ename",ename);
        cv.put("esalary",esalary);
        cv.put("edesignation",edesignation);
        sdb.update("emoplyee",cv,"_id="+pos,null);
    }

    public void deletedata(int pos){
        sdb.delete("emoplyee","_id="+pos,null);
    }


    public void close(){
        sdb.close();
    }
    public class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table emoplyee(_id integer primary key, ename text, esalary text,edesignation text);" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
