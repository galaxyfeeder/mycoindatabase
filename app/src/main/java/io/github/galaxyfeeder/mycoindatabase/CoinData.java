package io.github.galaxyfeeder.mycoindatabase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * CoinData
 * Created by galaxyfeeder on 06/04/17.
 */

class CoinData {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CURRENCY, MySQLiteHelper.COLUMN_VALUE};

    CoinData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    void close() {
        dbHelper.close();
    }

    Coin createCoin(String currency, double value) {
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + Double.toString(value) + " " + currency);

        // Add data: Note that this method only provides title and author
        // Must modify the method to add the full data
        values.put(MySQLiteHelper.COLUMN_CURRENCY, currency);
        values.put(MySQLiteHelper.COLUMN_VALUE, value);

        // Invented data
        values.put(MySQLiteHelper.COLUMN_COUNTRY, "Do not know");
        values.put(MySQLiteHelper.COLUMN_YEAR, 1714);
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, "Standard coin");

        // Actual insertion of the data using the values variable
        long insertId = database.insert(MySQLiteHelper.TABLE_COINS, null,
                values);

        // Main activity calls this procedure to create a new book
        // and uses the result to update the listview.
        // Therefore, we need to get the data from the database
        // (you can use this as a query example)
        // to feed the view.
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COINS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Coin newCoin = cursorToCoin(cursor);

        // Do not forget to close the cursor
        cursor.close();

        // Return the book
        return newCoin;
    }

    Coin addCoin(Coin coin){
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + Double.toString(coin.getValue()) + " " + coin.getCurrency());

        values.put(MySQLiteHelper.COLUMN_CURRENCY, coin.getCurrency());
        values.put(MySQLiteHelper.COLUMN_VALUE, coin.getValue());
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, coin.getDescription());
        values.put(MySQLiteHelper.COLUMN_YEAR, coin.getYear());
        values.put(MySQLiteHelper.COLUMN_COUNTRY, coin.getCountry());

        long insertId = database.insert(MySQLiteHelper.TABLE_COINS, null, values);
        coin.setId(insertId);

        return coin;
    }

    void deleteCoin(Coin coin) {
        long id = coin.getId();
        Log.d("Deleting", "Coin deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COINS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    List<Coin> getAllCoins() {
        List<Coin> coins = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COINS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Coin coin = cursorToCoin(cursor);
            coins.add(coin);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return coins;
    }

    private Coin cursorToCoin(Cursor cursor) {
        Coin coin = new Coin();
        coin.setId(cursor.getLong(0));
        coin.setCurrency(cursor.getString(1));
        coin.setValue(cursor.getDouble(2));
        return coin;
    }
}