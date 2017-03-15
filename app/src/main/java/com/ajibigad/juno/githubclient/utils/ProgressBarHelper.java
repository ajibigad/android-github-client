package com.ajibigad.juno.githubclient.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;

/**
 * Created by Julius on 13/03/2017.
 */
public class ProgressBarHelper {

    private ProgressDialog dialog;
    private Context context;

    public ProgressBarHelper(Context context){
        this.context = context;
    }

    public void showProgressDialog(String operationMessage){
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(operationMessage);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissProgressDialog(){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
