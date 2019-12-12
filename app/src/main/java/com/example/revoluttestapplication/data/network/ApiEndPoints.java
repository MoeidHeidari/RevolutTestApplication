package com.example.revoluttestapplication.data.network;

import com.example.revoluttestapplication.BuildConfig;

/**
 * a final class to store all the endpoints as static string fields
 * @author Moeid Heidari
 * @version 1.0
 */
public final class ApiEndPoints
{

    /**
     * an endpoint to get the list of rates dynamically
     */
    public static final String GET_CURRENCIES_API_END_POINT = BuildConfig.BASE_URL + "/latest?base=";

    private ApiEndPoints() {
        // prevent class instantiation
    }

    
}
