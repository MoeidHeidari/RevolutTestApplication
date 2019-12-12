package com.example.revoluttestapplication.dependencyInjection.component;


import com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations.PerActivity;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjection.ActivityModule;
import com.example.revoluttestapplication.views.mainActivity.MainActivity;

import dagger.Component;

/**
 * this interface acts as a component for any kind of injection related to activities
 * @author moeidheidari
 * @version 1.0
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent
{
    /**
     * inject MainActivity
     * @param activity
     */
    void inject(MainActivity activity);

}
