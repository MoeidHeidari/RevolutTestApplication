package com.example.revoluttestapplication.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * app cheduler provider to provide scheduler for IO,UI,NewThread,Single, and Trampoling
 * @author moeidheidari
 * @version 1.0
 */
public class AppSchedulerProvider implements SchedulerProvider
{


    @Override
    public Scheduler ui()
    {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation()
    {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io()
    {
        return Schedulers.io();
    }

    @Override
    public Scheduler newThread()
    {
        return Schedulers.newThread();
    }

    @Override
    public void shutDown()
    {
        Schedulers.shutdown();
    }

    @Override
    public void start()
    {
        Schedulers.start();
    }

    @Override
    public Scheduler single()
    {
        return Schedulers.single();
    }

    @Override
    public Scheduler trampoling()
    {
        return Schedulers.trampoline();
    }

}
