package com.example.revoluttestapplication.views.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.revoluttestapplication.App;
import com.example.revoluttestapplication.R;
import com.example.revoluttestapplication.dependencyInjection.component.ActivityComponent;
import com.example.revoluttestapplication.dependencyInjection.component.DaggerActivityComponent;
import com.example.revoluttestapplication.dependencyInjection.dependencyInjection.ActivityModule;
import com.example.revoluttestapplication.utils.UiUtils;
import com.example.revoluttestapplication.views.base.interfaces.BaseMvpView;
import com.google.android.material.snackbar.Snackbar;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * this base activity is defined to generalize some kind of operations which are supose to be done repeatedly in any activity
 * and implemented the BaseMvpView to implement the operations defined in BaseMvpView
 * generally speaking it acts as a base activity which is a child of AppCompatActivity itself.
 * @author moeidheidari
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {

    private ProgressDialog progressDialog;

    private ActivityComponent activityComponent;

    private Unbinder mUnBinder;


    /**
     * entry point of eny activity which extends this Base activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.Builder builder = DaggerActivityComponent.builder();
        builder.activityModule(new ActivityModule(this));
        builder.applicationComponent(((App) getApplication()).getmApplicationComponent());
        activityComponent = builder.build();

    }

    /**
     *
     * @return ActivityComponent
     */
    public ActivityComponent getActivityComponent()
    {
        return activityComponent;
    }

    /**
     *
     * @param activityComponent
     */
    public void setActivityComponent(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * request any kind of runtime permission
     * @param permissions
     * @param requestCode
     */
    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void request(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

    }


    /**
     * check if a specific kind of permission has been granted or not
     * @param permission
     * @return boolean
     */
    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * start the loading progress bar
     * @see UiUtils
     */
    @Override
    public void startLoading() {

            progressDialog = UiUtils.showLoadingDialog(this);

    }

    /**
     * ends the loading progress bar
     * @see UiUtils
     */
    @Override
    public void endLoading() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.cancel();
        }

    }

    /**
     * throws an error
     * @param stringRes
     */
    @Override
    public void onError(int stringRes) {
        onError(getString(stringRes));

    }

    /**
     * overloaded version of onError(...) function
     * @param messageBody
     */
    @Override
    public void onError(String messageBody) {
        if (messageBody != null) {
            showSnackBar(messageBody);
        } else {
            showSnackBar(getString(R.string.error));
        }

    }

    /**
     * shows an snack bar with a string reference
     * @param stringRes
     */
    @Override
    public void showSnackBar(int stringRes) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                getString(stringRes), Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.White));
        snackbar.show();

    }

    /**
     * overloaded version of showSnackBar(...) function with a direct string message
     * @param message
     */
    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.White));
        snackbar.show();

    }
    /**
     * shows a Toast with a string reference from string.xml
     * @param message
     */
    @Override
    public void showToast(int stringRes) {
        showToast(getString(stringRes));


    }

    /**
     * overloaded version of showToast(...) function with a direct string message
     * @param message
     */
    @Override
    public void showToast(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * useful to start any activity
     * @param intent
     */
    @Override
    public void startNewActivity(Intent intent)
    {
        startActivity(intent);

    }

    @Override
    public void reinitializeTheActivity() {

    }

    @Override
    public void checkNetworkConnection() {

    }

    /**
     * active or deactive the keyboard with the parameter
     * @param active
     */
    @Override
    public void activeKeyBoard(boolean active) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (active) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } else {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 1);
            }

        }


    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    /**
     * this abstract function should be override
     */
    protected abstract void setUp();
}
