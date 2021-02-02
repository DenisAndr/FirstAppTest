package de.ludetis.firstapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "card_sqlite_db";
    public static final String CARD_OWNER_NAME = "card_owner_name";
    public static final String CARD_PIN = "card_pincode";
    private static final String TABLE_NAME = "cards_table";
    public static final String _ID = "_id";
    private final SQLiteDatabase database;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        database = getWritableDatabase();
    }

    public void insert(String name, String pincode) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CARD_OWNER_NAME, name);
        contentValue.put(DatabaseHelper.CARD_PIN, pincode);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public int update(long _id, String name, String pincode) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CARD_OWNER_NAME, name);
        contentValues.put(DatabaseHelper.CARD_PIN, pincode);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.CARD_OWNER_NAME, DatabaseHelper.CARD_PIN };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CARD_OWNER_NAME + " TEXT NOT NULL, " + CARD_PIN + " TEXT);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor setect(int id) {
        Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + " = '"+ id +"'", null);
        return c;
    }
}
