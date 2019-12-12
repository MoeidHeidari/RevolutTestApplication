package com.example.revoluttestapplication.data.network.dataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Moeid Heidari
 * version 1.0
 * The Currensies data model to hold the currensies comming from api
 */
public class CurrenciesApiResponseModel {


    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Map<String,Float> rates;
    List<Rate> ratesList;
    //==================================================================================================================

    /**
     * get the rates as a list of Rate model
     * @see Rate
     * @return List<Rate>
     */
    public List<Rate> getRateList()
    {
        return ratesList;
    }

    /**
     * initialize the rates list and convert the map of name value pairs to a list
     */
    public void makeTheList()
    {
        ratesList=new ArrayList<>();
        if(null!=ratesList)
        {
            for (Map.Entry<String,Float> item:rates.entrySet())
            {
                ratesList.add(new Rate(item.getKey(),item.getValue(),0));
            }
        }



    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }

//==================================================================================================================
    /**
     * default constructor
     */
    public CurrenciesApiResponseModel()
    {
        ratesList=new ArrayList<>();
    }

    /**
     * overloaded constructor
     * @param base
     * @param date
     * @param rates
     */
    public CurrenciesApiResponseModel(String base, String date, Map<String, Float> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    /**
     * Rate data model to hold on the name and value of each rate name value pair
     * @author Moeid Heidari
     * @version 1.0
     */
    //==================================================================================================================
    public class Rate
    {
        // json properties
        String currencyName;
        float currencyRate;
        float convertedCurrency;

        public float getConvertedCurrency() {
            return convertedCurrency;
        }

        public void setConvertedCurrency(float convertedCurrency) {
            this.convertedCurrency = convertedCurrency;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public float getCurrencyRate() {
            return currencyRate;
        }

        public void setCurrencyRate(float currencyRate) {
            this.currencyRate = currencyRate;
        }

        /**
         * overloaded constructor
         * @param currencyName
         * @param currencyRate
         * @param convertedCurrency
         */
        public Rate(String currencyName, float currencyRate, float convertedCurrency) {
            this.currencyName = currencyName;
            this.currencyRate = currencyRate;
            this.convertedCurrency = convertedCurrency;
        }
    }
}