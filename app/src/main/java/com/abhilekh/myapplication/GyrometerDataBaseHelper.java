package com.abhilekh.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GyrometerDataBaseHelper extends SQLiteOpenHelper
{

    public GyrometerDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DBConstants.DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE " + DBConstants.GRYOMETER_TABLE_NAME +
                " (GyrometerId INTEGER PRIMARY KEY AUTOINCREMENT, TransactionId LONG ," +
                " XValue DOUBLE , YValue DOUBLE , ZValue DOUBLE  , CREATE_DATE_TIME  DATETIME) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBConstants.ACCELEROMETER_TABLE_NAME);
    }

    public boolean insertData(String transactionId,String xValue, String yValue, String zValue)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mmm:ss");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.GRYOMETER_COL_1,transactionId);
        contentValues.put(DBConstants.GRYOMETER_COL_2,xValue);
        contentValues.put(DBConstants.GRYOMETER_COL_3,yValue);
        contentValues.put(DBConstants.GRYOMETER_COL_4,zValue);
        contentValues.put(DBConstants.GRYOMETER_COL_5,sdf.format(new Date()));

        long result = db.insert(DBConstants.GRYOMETER_TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }
}