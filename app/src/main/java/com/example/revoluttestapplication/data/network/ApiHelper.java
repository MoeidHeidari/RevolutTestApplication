package com.example.revoluttestapplication.data.network;

import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import io.reactivex.Observable;

/**
 * ApiHelper interface for api calling operations
 * @author Moeid Heidari
 * @version 1.0
 */
public interface ApiHelper
{
    /**
     * call the rates api
     * @param base
     * @return Observable<CurrenciesApiResponseModel>
     */
    Observable<CurrenciesApiResponseModel> getCurrenciesApiCall(String base);
}
