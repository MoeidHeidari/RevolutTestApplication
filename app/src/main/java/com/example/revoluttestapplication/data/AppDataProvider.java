package com.example.revoluttestapplication.data;

import android.content.Context;
import com.example.revoluttestapplication.data.network.ApiHelper;
import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations.ApplicationContext;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * this class acts a a general data manager (provider)
 * this class as a singletone class is responsible to provide any kid of data either from network,pref or database
 * @author moeidheidari
 * @version 1.0
 */
public class AppDataProvider implements DataProvider
{
    private final Context context;
    private final ApiHelper apiHelper;

    /**
     * inject this class to application component
     * @param context
     * @param apiHelper
     */
    @Inject
    public AppDataProvider(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        this.context = context;
        this.apiHelper = apiHelper;
    }

    /**
     * do the operation of api calls from api helper and provide the data to consumers
     * @param base
     * @return Observable<CurrenciesApiResponseModel>
     */
    @Override
    public Observable<CurrenciesApiResponseModel> getCurrenciesApiCall(String base) {
        return apiHelper.getCurrenciesApiCall(base);
    }
}
