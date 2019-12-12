package com.example.revoluttestapplication.views.base;

import com.androidnetworking.error.ANError;
import com.example.revoluttestapplication.R;
import com.example.revoluttestapplication.data.DataProvider;
import com.example.revoluttestapplication.views.base.interfaces.BaseMvpPresenter;
import com.example.revoluttestapplication.views.base.interfaces.BaseMvpView;
import com.example.revoluttestapplication.utils.rx.*;
import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import io.reactivex.disposables.CompositeDisposable;

/**
 * BasePresenter as base layer for presenter layer in Mvp architecture to hold some basic operations which are related to
 * business logic layer (Presenter) and any presenter should extends this base class
 * @author moeidheidari
 * @version 1.0
 * @param <V>
 */
public class BasePresenter <V extends BaseMvpView> implements BaseMvpPresenter<V>
{

    private static final String TAG = "BasePresenter";

    private final DataProvider mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;
    private V mMvpView;

    @Inject
    public BasePresenter(DataProvider dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    /**
     * gets mvp view attached to this presenter layer
     * @return
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * check if view is attached to presenter layer or not
     * @return
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void onAttach(V baseMvpView)
    {
        mMvpView = baseMvpView;

    }

    /**
     * provides the Scheduler provider
     * @return SchedulerProvider
     */
    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    /**
     * provides the CompositeDisposable
     * @return CompositeDisposable
     */
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    /**
     * provides the DataProvider
     * @return DataProvider
     */
    public DataProvider getDataProvider() {
        return mDataManager;
    }


    @Override
    public void onDetach()
    {
        mCompositeDisposable.dispose();
        mMvpView = null;

    }

    /**
     * to hold some http Error codes
     * @param error
     */
    @Override
    public void handleApiError(ANError error)
    {

        if (error == null || error.getErrorBody() == null) {
            getMvpView().onError(R.string.error);
            return;
        }
        switch (error.getErrorCode())
        {
            case HttpsURLConnection.HTTP_UNAUTHORIZED:
                getMvpView().onError(R.string.HTTP_UNAUTHORIZED);
            case HttpsURLConnection.HTTP_FORBIDDEN:
                getMvpView().onError(R.string.HTTP_FORBIDDEN);
            case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                getMvpView().onError(R.string.HTTP_INTERNAL_ERROR);
            case HttpsURLConnection.HTTP_NOT_FOUND:
                getMvpView().onError(R.string.HTTP_NOT_FOUND);
            default:
                getMvpView().onError(error.getMessage());
        }


    }

    /**
     * a costume defined RuntimeException class to throw some errors related to MvpView attachement
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Presenter.onAttach(MvpView) should be called before request data from presenter");
        }
    }
}
