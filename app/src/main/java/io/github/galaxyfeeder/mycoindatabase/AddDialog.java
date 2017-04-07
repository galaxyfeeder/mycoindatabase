package io.github.galaxyfeeder.mycoindatabase;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * AddDialog
 * Created by galaxyfeeder on 07/04/17.
 */

class AddDialog extends Dialog implements View.OnClickListener{
    private EditText currency, value, year, description, country;
    private AddListener listener;

    AddDialog(Context context) {
        super(context);
        this.setContentView(R.layout.add_dialog);

        currency = (EditText) this.findViewById(R.id.et_currency);
        value = (EditText) this.findViewById(R.id.et_value);
        year = (EditText) this.findViewById(R.id.et_year);
        description = (EditText) this.findViewById(R.id.et_description);
        country = (EditText) this.findViewById(R.id.et_country);

        Button cancel = (Button) this.findViewById(R.id.cancel_button);
        Button add = (Button) this.findViewById(R.id.add_button);

        cancel.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    void setAddListener(AddListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_button:
                Coin coin = new Coin();
                coin.setValue(Float.parseFloat(value.getText().toString()));
                coin.setCurrency(currency.getText().toString());
                coin.setCountry(country.getText().toString());
                coin.setYear(Integer.parseInt(year.getText().toString()));
                coin.setDescription(description.getText().toString());
                listener.onAddCoin(coin);
                this.dismiss();
                break;
            case R.id.cancel_button:
                this.dismiss();
                break;
        }
    }
}
