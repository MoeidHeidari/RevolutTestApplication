package com.example.revoluttestapplication.views.base.interfaces;

public interface BaseSubMvpView extends BaseMvpView
{
    void attachBaseMvpView(BaseMvpView mvpView);

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();


}
