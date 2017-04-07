package io.github.galaxyfeeder.mycoindatabase;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * MySQLiteHelper
 * Created by galaxyfeeder on 06/04/17.
 */

public class MainActivity extends ListActivity {
    private CoinData coinData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        coinData = new CoinData(this);
        coinData.open();

        List<Coin> values = coinData.getAllCoins();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Coin> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked") final
        ArrayAdapter<Coin> adapter = (ArrayAdapter<Coin>) getListAdapter();
        Coin coin;
        switch (view.getId()) {
            case R.id.add:

                final AddDialog dialog = new AddDialog(this);
                dialog.setAddListener(new AddListener() {
                    @Override
                    public void onAddCoin(Coin coin) {
                        coinData.addCoin(coin);
                        adapter.add(coin);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();

                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    coin = (Coin) getListAdapter().getItem(0);
                    coinData.deleteCoin(coin);
                    adapter.remove(coin);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        coinData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        coinData.close();
        super.onPause();
    }

}