package com.example.revoluttestapplication.dependencyInjection.component;


import android.app.Application;
import android.content.Context;

import com.example.revoluttestapplication.App;
import com.example.revoluttestapplication.data.DataProvider;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations.ApplicationContext;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjection.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * this interface acts as a component for any kind of injection related to application
 *
 * @author moeidheidari
 * @version 1.0
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext
    Context context();

    /**
     * inject App
     *
     * @param app
     */
    void inject(App app);

    /**
     * @return Application
     * @see Application
     */
    Application application();

    /**
     * @return DataProvider
     * @see DataProvider
     */
    DataProvider getDataProvider();

}
