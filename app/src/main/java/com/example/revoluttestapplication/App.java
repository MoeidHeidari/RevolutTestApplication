package com.example.revoluttestapplication;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.revoluttestapplication.data.DataProvider;
import com.example.revoluttestapplication.dependencyInjection.component.ApplicationComponent;
import com.example.revoluttestapplication.dependencyInjection.component.DaggerApplicationComponent;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjection.ApplicationModule;
import com.example.revoluttestapplication.utils.logger.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author moeidheidari
 * @version 1.0
 */

public class App extends Application
{
    @Inject
    DataProvider mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;



    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.initialize();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);


    }

    public DataProvider getmDataManager() {
        return mDataManager;
    }

    public void setmDataManager(DataProvider mDataManager) {
        this.mDataManager = mDataManager;
    }

    public CalligraphyConfig getmCalligraphyConfig() {
        return mCalligraphyConfig;
    }

    public void setmCalligraphyConfig(CalligraphyConfig mCalligraphyConfig) {
        this.mCalligraphyConfig = mCalligraphyConfig;
    }

    public ApplicationComponent getmApplicationComponent() {
        return mApplicationComponent;
    }

    public void setmApplicationComponent(ApplicationComponent mApplicationComponent) {
        this.mApplicationComponent = mApplicationComponent;
    }
}
