package com.example.revoluttestapplication.dependencyInjection.dependencyInjection;

import android.app.Application;
import android.content.Context;
import com.example.revoluttestapplication.R;
import com.example.revoluttestapplication.data.AppDataProvider;
import com.example.revoluttestapplication.data.DataProvider;
import com.example.revoluttestapplication.data.network.ApiHelper;
import com.example.revoluttestapplication.data.network.AppApiHelper;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations.ApplicationContext;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * application module to provide DataProcider,Context,ApiHelper and CalligraphyConfig
 * @author moeidheidari
 * @version 1.0
 */
@Module
public class ApplicationModule
{
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    /**
     * provides Context
     * @return Context
     */
    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    /**
     * provides Application
     * @return Application
     * @see Application
     */
    @Provides
    Application provideApplication() {
        return mApplication;
    }


    /**
     * provides DataProvider
     * @param AppDataProvider
     * @return DataProvider
     * @see DataProvider
     */
    @Provides
    @Singleton
    DataProvider provideDataProvider(AppDataProvider appDataManager) {
        return appDataManager;
    }

    /**
     * provides ApiHelper
     * @param AppApiHelper
     * @return ApiHelper
     * @see ApiHelper
     */
    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper)
    {
        return appApiHelper;
    }


    /**
     * provides CalligraphyConfig
     * @return CalligraphyConfig
     * @see CalligraphyConfig
     */
    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
