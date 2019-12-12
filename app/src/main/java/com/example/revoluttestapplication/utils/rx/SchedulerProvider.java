package com.example.revoluttestapplication.utils.rx;

import io.reactivex.Scheduler;

/**
 * @author moeidheidari
 * @version 1.0
 */

public interface SchedulerProvider
{

    void shutDown();

    void start();
    Scheduler ui();

    Scheduler computation();

    Scheduler io();

    Scheduler newThread();

    Scheduler single();

    Scheduler trampoling();



}
