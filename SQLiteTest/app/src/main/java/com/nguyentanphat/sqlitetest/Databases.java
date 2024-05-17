package com.nguyentanphat.sqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Databases extends SQLiteOpenHelper {
    public static final String DB_NAME = "tours.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Tour";
    public static final String COL_CODE = "TourCode";
    public static final String COL_NAME = "TourName";
    public static final String COL_PRICE = "TourPrice";
    public static final String COL_DESCRIPTION = "TourDescription";
    public static final String COL_PHOTO = "TourPhoto";

    //constructor
    public Databases(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(50), " + COL_PRICE + " REAL, " + COL_DESCRIPTION + " VARCHAR(50), " + COL_PHOTO + " VARCHAR(50) )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    //SELECT...
    public Cursor queryData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    //INSERT, UPDATE, DELETE...
    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public int getNumbOfRows(){
        Cursor cursor = queryData("SELECT * FROM " + TBL_NAME);
        int numbOfRows = cursor.getCount();
        cursor.close();
        return numbOfRows;
    }

    public void createSampleData(){
        if(getNumbOfRows() == 0){
            try {
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bò sữa Long Thành', 190000, 'Giá rẻ', 'Hihi.png')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Đông Sơn', 20000,'Giá rẻ', 'Hihi.png')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Tây Nguyên', 30000,'Giá rẻ', 'Hihi.png')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Hà Nội', 25000, 'Giá rẻ', 'Hihi.png')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Cao Bằng', 19000, 'Giá rẻ', 'Hihi.png')");
            }catch (Exception e){
                Log.e("Error: ", e.getMessage().toString());
            }
        }
    }
}
