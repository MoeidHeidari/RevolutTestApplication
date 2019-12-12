package com.example.revoluttestapplication.views.mainActivity;

import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import com.example.revoluttestapplication.views.base.interfaces.BaseMvpView;

/**
 * @author moeidheidari
 * @version 1.0
 * @param <V>
 */

public interface MainActivityMvpView extends BaseMvpView
{
    void onFetchCurrencies(CurrenciesApiResponseModel responseModel);


}
