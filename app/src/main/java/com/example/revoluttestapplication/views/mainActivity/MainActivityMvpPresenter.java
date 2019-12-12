package com.example.revoluttestapplication.views.mainActivity;

import com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations.PerActivity;
import com.example.revoluttestapplication.views.base.interfaces.BaseMvpPresenter;

/**
 * @author moeidheidari
 * @version 1.0
 * @param <V>
 */
@PerActivity
public interface MainActivityMvpPresenter <V extends MainActivityMvpView> extends BaseMvpPresenter<V>
{

    void getCurrencies(String base);
    void updateTheRates(String base);

}
