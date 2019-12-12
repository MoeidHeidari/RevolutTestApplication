package com.example.revoluttestapplication.viewHolders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.revoluttestapplication.R;
import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import com.example.revoluttestapplication.utils.CurrencyUtil;
import com.haipq.android.flagkit.FlagImageView;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * this view holder is responsible to hold the views for recyclerView list items
 * @author moeidheidari
 * @version 1.0
 */
@LayoutId(R.layout.currency_converter_list_item)
public class CurrencyViewHolder extends ItemViewHolder<CurrenciesApiResponseModel.Rate> {

    @ViewId(R.id.Currency_Converter_RateTextView_Item)
    TextView Currency_Converter_RateTextView_Item;
    @ViewId(R.id.Currency_Converter_EditText_Item)
    EditText Currency_Converter_EditText_Item;
    @ViewId(R.id.currensies_list_item_flag)
    FlagImageView currensies_list_item_flag;

    /**
     * overloaded constructor
     * @param view
     */
    public CurrencyViewHolder(View view) {
        super(view);
    }

    /**
     * this method will bind the data of List<CurrenciesApiResponseModel.Rate> to the list items
     * @param item
     * @param positionInfo
     */
    @Override
    public void onSetValues(CurrenciesApiResponseModel.Rate item, PositionInfo positionInfo) {
        Currency_Converter_RateTextView_Item.setText(item.getCurrencyName());
        Currency_Converter_EditText_Item.setText(String.valueOf(item.getConvertedCurrency()));
        currensies_list_item_flag.setCountryCode(CurrencyUtil.getCountryCodeFromCurrencyCode(item.getCurrencyName()));
    }

    /**
     * sets the listener to the list items and emit them to the outside
     */
    @Override
    public void onSetListeners() {

        getView().setOnClickListener(v -> {
            //Get the custom listener and call the method.
            OnItemClick listener = getListener(OnItemClick.class);
            if (listener != null) {
                listener.onClick(getItem());
            }
        });

        Currency_Converter_EditText_Item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                CurrencyViewHolder.OnItemClick listener = getListener(CurrencyViewHolder.OnItemClick.class);
                if (listener != null)
                {
                    listener.onTextChanged(getItem(), Currency_Converter_EditText_Item.getText().toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }


    /**
     * to hold on the events of an individual list item and emit it to the adapter to be accesible from outside
     * @author moeidheidari
     * @version 1.0
     */
    public interface OnItemClick
    {
        /**
         * on click listener for an individual list item
         * @param item
         */
        void onClick(CurrenciesApiResponseModel.Rate item);

        /**
         * whenever the user changes the currency value edit text this event will be emited
         * @param item
         */
        void onTextChanged(CurrenciesApiResponseModel.Rate item, String amount);
    }

}