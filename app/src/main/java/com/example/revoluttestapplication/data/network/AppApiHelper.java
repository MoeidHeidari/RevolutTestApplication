package com.example.revoluttestapplication.data.network;

import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * this class is used to implement the api calling operations with rxjava
 * @author Moeid Heidari
 * @version 1.0
 */
public class AppApiHelper implements ApiHelper
{
    /**
     * injectable contructor
     */
    @Inject
    public AppApiHelper() {}


    /**
     * implementation of rate list api calling from the end point
     * @param base
     * @return Observable<CurrenciesApiResponseModel>
     */
    @Override
    public Observable<CurrenciesApiResponseModel> getCurrenciesApiCall(String base)
    {
        return Rx2AndroidNetworking.get(ApiEndPoints.GET_CURRENCIES_API_END_POINT+base)
                .doNotCacheResponse()
                .build()
                .getObjectObservable(CurrenciesApiResponseModel.class);
    }
}
