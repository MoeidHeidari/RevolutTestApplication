package com.example.revoluttestapplication.dependencyInjection.dependencyInjection;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations.ActivityContext;
import com.example.revoluttestapplication.utils.rx.AppSchedulerProvider;
import com.example.revoluttestapplication.utils.rx.SchedulerProvider;
import com.example.revoluttestapplication.views.mainActivity.MainActivityMvpPresenter;
import com.example.revoluttestapplication.views.mainActivity.MainActivityMvpView;
import com.example.revoluttestapplication.views.mainActivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * acts as a module of activities in dependency injection system
 * @author moeidheidari
 * @version 1.0
 */
@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * provides AppCompatActivity
     * @return AppCompatActivity
     * @see AppCompatActivity
     */
    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    /**
     * provides CompositeDisposable
     * @return CompositeDisposable
     * @see CompositeDisposable
     */
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    /**
     * provides SchedulerProvider
     * @return SchedulerProvider
     * @see SchedulerProvider
     */
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    /**
     * provides Context
     * @return Context
     * @see Context
     */
    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    /**
     * provides MainActivityMvpPresenter<MainActivityMvpView>
     * @return MainActivityMvpPresenter<MainActivityMvpView>
     * @see MainActivityMvpPresenter<MainActivityMvpView>
     */
    @Provides
    MainActivityMvpPresenter<MainActivityMvpView> provideMainActivityPresenter(
            MainActivityPresenter<MainActivityMvpView> presenter) {
        return presenter;
    }


}
