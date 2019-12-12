package com.example.revoluttestapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.example.revoluttestapplication.R;

/**
 * responsible to hold the general operations related to ui which are independent fron an specific activity
 * @author moeidheidari
 * @version 1.0
 */

public class UiUtils
{
    /**
     * initialize and return a cusotm progress bar to use in MvpView as a comprehensive loading progress dialog
     * @param context
     * @return ProgressDialog
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

}
