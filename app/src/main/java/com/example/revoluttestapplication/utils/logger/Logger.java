package com.example.revoluttestapplication.utils.logger;

/**
 * @author moeidheidari
 * @version 1.0
 */
public interface Logger
{
     void d(String s, Object... objects);

    void d(Throwable throwable, String s, Object... objects);

    void i(String s, Object... objects);

    void i(Throwable throwable, String s, Object... objects);

    void w(String s, Object... objects);

    void w(Throwable throwable, String s, Object... objects);

    void e(String s, Object... objects);

    void e(Throwable throwable, String s, Object... objects);

}
