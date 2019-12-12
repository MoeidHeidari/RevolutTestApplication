package com.example.revoluttestapplication.views.base.interfaces;

import com.androidnetworking.error.ANError;

public interface BaseMvpPresenter <v extends BaseMvpView>
{

    void onAttach(v baseMvpView);

    void onDetach();

    void handleApiError(ANError error);



}
