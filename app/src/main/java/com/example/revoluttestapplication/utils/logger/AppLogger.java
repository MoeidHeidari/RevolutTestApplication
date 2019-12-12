package com.example.revoluttestapplication.utils.logger;

import com.example.revoluttestapplication.BuildConfig;
import timber.log.Timber;

/**
 * logger class to manage the logging operation using Timber logger library
 * @author moeidheidari
 * @version 1.0
 */
public class AppLogger implements Logger
{

    private static AppLogger instance;

    public static AppLogger initialize()
    {
        if(BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        return instance;
    }


    @Override
    public void d(String s, Object... objects)
    {
        Timber.d(s, objects);
    }

    @Override
    public void d(Throwable throwable, String s, Object... objects)
    {
        Timber.d(throwable, s, objects);
    }

    @Override
    public void i(String s, Object... objects)
    {
        Timber.i(s, objects);
    }

    @Override
    public void i(Throwable throwable, String s, Object... objects)
    {
        Timber.i(throwable, s, objects);
    }

    @Override
    public void w(String s, Object... objects)
    {
        Timber.w(s, objects);
    }

    @Override
    public void w(Throwable throwable, String s, Object... objects)
    {
        Timber.w(throwable, s, objects);
    }

    @Override
    public void e(String s, Object... objects)
    {
        Timber.e(s, objects);
    }

    @Override
    public void e(Throwable throwable, String s, Object... objects)
    {
        Timber.e(throwable, s, objects);
    }
}
