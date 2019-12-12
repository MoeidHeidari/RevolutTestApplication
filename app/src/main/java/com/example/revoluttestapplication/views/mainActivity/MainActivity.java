package com.example.revoluttestapplication.views.mainActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.revoluttestapplication.R;
import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import com.example.revoluttestapplication.viewHolders.CurrencyViewHolder;
import com.example.revoluttestapplication.views.base.BaseActivity;
import java.util.Collections;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

/**
 * main activity
 * @author moeidheidari
 * @version 1.0
 */
public class MainActivity extends BaseActivity implements MainActivityMvpView {


    // View binder
    @BindView(R.id.CurrensiesRecyclerView)
    RecyclerView CurrensiesRecyclerView;
    CurrenciesApiResponseModel rates;
    boolean isInitialized;


    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> presenter;


    /**
     * an static method to return the intent related to this activity from anywhere
     * @param context
     * @return Intent
     */
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(MainActivity.this);

        setUp();
    }

    /**
     * everything starts from here
     */
    @Override
    protected void setUp() {presenter.updateTheRates("EUR");}

    /**
     * initialize the recycler view to make it ready for adapter attachment
     */
    private void initializeRecyclerView() {
        isInitialized = false;
        rates = new CurrenciesApiResponseModel();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        CurrensiesRecyclerView.setLayoutManager(linearLayoutManager);
        CurrensiesRecyclerView.setItemAnimator(null);
    }


    /**
     * we jump here whenever we get the response of Currencies api point
     * @param responseModel
     */
    @Override
    public void onFetchCurrencies(CurrenciesApiResponseModel responseModel) {
        responseModel.makeTheList();
        if (!isInitialized) {
            initializeRecyclerView();
            rates.getRateList().addAll(responseModel.getRateList());
            CurrencyViewHolder.OnItemClick mListener = new CurrencyViewHolder.OnItemClick() {
                @Override
                public void onClick(CurrenciesApiResponseModel.Rate item) {
                    int curentPosition = rates.getRateList().indexOf(item);
                    Collections.swap(rates.getRateList(), rates.getRateList().indexOf(item), 0);
                    CurrensiesRecyclerView.getAdapter().notifyItemChanged(0);
                    CurrensiesRecyclerView.getAdapter().notifyItemChanged(curentPosition);
                }
                @Override
                public void onTextChanged(CurrenciesApiResponseModel.Rate rate, String rateAmount) {


                    if (rates.getRateList().indexOf(rate) == 0) {
                        if (rate.getCurrencyRate() <= 0) {
                            rates.getRateList().get(0).setConvertedCurrency(0f);
                        }
                        try {
                            rates.getRateList().get(0).setConvertedCurrency(Float.parseFloat(rateAmount));
                            for (int i = 1; i < rates.getRateList().size(); i++) {
                                rates.getRateList().get(i).setConvertedCurrency(rates.getRateList().get(0).getConvertedCurrency() * rates.getRateList().get(i).getCurrencyRate());
                            }

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            rates.getRateList().get(0).setConvertedCurrency(0f);
                        }
                        if (!CurrensiesRecyclerView.isComputingLayout()) {
                            CurrensiesRecyclerView.getAdapter().notifyItemRangeChanged(1, rates.getRateList().size());
                        }
                    }


                }
            };
            CurrensiesRecyclerView.setAdapter(new EasyRecyclerAdapter<CurrenciesApiResponseModel.Rate>(this, CurrencyViewHolder.class, rates.getRateList(), mListener));
            isInitialized = true;
        } else
            {

            for (int i = 1; i < rates.getRateList().size(); i++) {
                rates.getRateList().get(i).setCurrencyRate(responseModel.getRateList().get(i).getCurrencyRate());
                rates.getRateList().get(i).setConvertedCurrency(rates.getRateList().get(0).getConvertedCurrency() * rates.getRateList().get(i).getCurrencyRate());
            }

            if (!CurrensiesRecyclerView.isComputingLayout()) {
                CurrensiesRecyclerView.getAdapter().notifyItemRangeChanged(1, rates.getRateList().size());
            }
        }

    }


}
