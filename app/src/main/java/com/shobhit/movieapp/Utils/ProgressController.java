package com.shobhit.movieapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by abcplusd on 04/12/17.
 */

public class ProgressController {
    private static ProgressController progressController;

    public ProgressDialog m_Dialog;
    private static Context context;

    private ProgressController(Context context) {
        this.context = context;

    }

    public static ProgressController getInstance() {
        if (progressController == null) {
            progressController = new ProgressController(context);
        }
        return progressController;
    }

    public void showProgress(Context m_Context, String message) {

        m_Dialog = new ProgressDialog(m_Context, ProgressDialog.THEME_HOLO_DARK);
        m_Dialog.setMessage(message);
        /** 'Window.FEATURE_NO_TITLE' - Used to hide the mTitle */
        m_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //progressDialog.setContentView(R.layout.actionbar_indeterminate_progress);
        m_Dialog.setCanceledOnTouchOutside(false);
        m_Dialog.setIndeterminate(true);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.show();
    }

    public void showProgress(Context m_Context, String message, Boolean setCancelable) {


        m_Dialog = new ProgressDialog(m_Context, ProgressDialog.THEME_HOLO_DARK);
        m_Dialog.setMessage(message);
        /** 'Window.FEATURE_NO_TITLE' - Used to hide the mTitle */
        m_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //progressDialog.setContentView(R.layout.actionbar_indeterminate_progress);
        m_Dialog.setCanceledOnTouchOutside(setCancelable);
        m_Dialog.setIndeterminate(true);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.show();
    }
}

