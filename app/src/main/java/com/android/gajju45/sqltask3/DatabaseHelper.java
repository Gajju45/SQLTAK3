package com.android.gajju45.sqltask3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper   extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "Debox.db";
    public static final  String TABLE_NAME = "debox_table";
    public static final  String COL_1 = "ID";
    public static final  String COL_2 = "DATE_TIME";
    public static final  String COL_3 = "MOBILENO";
    public static final  String COL_4 = "EMAILID";
    public static final  String COL_5 = "PURPOSE";
    public static final  String COL_6 = "COMMENTS";
    public static final  String COL_7 = "LOCATION";
    public static final  String COL_8 = "COMPANY";
    public static final  String COL_9 = "MEETING";




    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,DATE_TIME INTEGER,MOBILENO INTEGER,EMAILID TEXT,PURPOSE TEXT,COMMENTS TEXT,LOCATION TEXT, COMPANY TEXT,MEETING TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String date, String mobile, String email, String purpose , String comment, String location,String meeting, String company  ){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,mobile);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,purpose);
        contentValues.put(COL_6,comment);
        contentValues.put(COL_7,location);
        contentValues.put(COL_8,meeting);
        contentValues.put(COL_9,company);


        long success= db.insert(TABLE_NAME,null,contentValues);
        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public boolean updateData( String id,String date, String mobile, String email, String purpose , String comment, String location){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,mobile);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,purpose);
        contentValues.put(COL_6,comment);
        contentValues.put(COL_7,location);

        db.update(TABLE_NAME,contentValues,"ID= ?", new String[]{id});
        return true;

    }

    public int deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID = ? ",new String[]{id});


    }
}
