package com.example.revoluttestapplication.views.mainActivity;

import com.androidnetworking.error.ANError;
import com.example.revoluttestapplication.data.DataProvider;
import com.example.revoluttestapplication.data.network.dataModels.CurrenciesApiResponseModel;
import com.example.revoluttestapplication.utils.rx.SchedulerProvider;
import com.example.revoluttestapplication.views.base.BasePresenter;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author moeidheidari
 * @version 1.0
 * @param <V>
 */

public class MainActivityPresenter <V extends MainActivityMvpView> extends BasePresenter<V>
        implements MainActivityMvpPresenter<V>
{
    private static final String TAG = "MainActivityPresenter";

    @Inject
    public MainActivityPresenter(DataProvider dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getCurrencies(String base)
    {
        getCompositeDisposable().add(getDataProvider().getCurrenciesApiCall(base).subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<CurrenciesApiResponseModel>() {
            @Override
            public void accept(CurrenciesApiResponseModel responseModel) throws Exception
            {
                if(null!=responseModel)
                {
                    getMvpView().onFetchCurrencies(responseModel);
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception
            {
                if(!isViewAttached())
                {
                    return;
                }
                getMvpView().endLoading();

                getMvpView().showToast(throwable.getMessage());
                if (throwable instanceof ANError) {
                    ANError anError = (ANError) throwable;
                    handleApiError(anError);
                }

            }
        }));

    }

    @Override
    public void updateTheRates(String base)
    {

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Long value)
                    {
                        getCurrencies(base);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });




    }
}
