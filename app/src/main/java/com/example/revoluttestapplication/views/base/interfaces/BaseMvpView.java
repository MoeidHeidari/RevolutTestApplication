package com.example.revoluttestapplication.views.base.interfaces;

import android.content.Intent;

import androidx.annotation.StringRes;

public interface BaseMvpView
{
    void startLoading();

    void endLoading();

    void onError(@StringRes int stringRes);

    void onError(String messageBody);

    void showSnackBar(@StringRes int stringRes);

    void showSnackBar(String message);

    void showToast(@StringRes int stringRes);

    void showToast(String message);

    void startNewActivity(Intent intent);

    void reinitializeTheActivity();

    void checkNetworkConnection();

    void activeKeyBoard(boolean active);

    void request(String[] permissions, int requestCode);

    boolean hasPermission(String permission);




}
