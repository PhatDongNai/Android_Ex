package com.nguyentanphat.sqlite_ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Databases extends SQLiteOpenHelper {

    public static final String DB_NAME = "products.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Product";
    public static final String COL_CODE = "ProductCode";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    //constructor
    public Databases(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(50), " + COL_PRICE + " REAL)";
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
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Heineken', 19000)");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, '333', 20000)");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Tiger', 30000)");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Huda', 25000)");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Blanc', 19000)");
            }catch (Exception e){
                Log.e("Error: ", e.getMessage().toString());
            }
        }
    }
}