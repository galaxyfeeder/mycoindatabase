package io.github.galaxyfeeder.mycoindatabse;

/**
 * MySQLiteHelper
 * Created by galaxyfeeder on 06/04/17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


class MySQLiteHelper extends SQLiteOpenHelper {

    static final String TABLE_COINS = "coins";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_CURRENCY = "currency";
    static final String COLUMN_VALUE = "value";
    static final String COLUMN_YEAR = "year";
    static final String COLUMN_COUNTRY = "country";
    static final String COLUMN_DESCRIPTION = "description";

    private static final String DATABASE_NAME = "coins.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_COINS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CURRENCY + " text not null, "
            + COLUMN_VALUE + " real not null, "
            + COLUMN_YEAR + " integer, "
            + COLUMN_COUNTRY + " text not null, "
            + COLUMN_DESCRIPTION + " text"
            + ");";

    MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COINS);
        onCreate(db);
    }

}