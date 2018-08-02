package com.wf.aloha.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;

public class DialogCreatHelper {

    public static void showDialog(final FragmentManager fragmentManager, final String title, final String content, final ResultDialogListener<Integer> resultDialogListener, DialogFragmentDes.OnCancelListener onCancelListener, boolean cancelable) {
        DialogFragmentDes instance = DialogFragmentDes.getInstance(onCancelListener, new OnCreateDialog() {
            @Override
            public Dialog creatDialog(Context context) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                AlertDialog alertDialog = builder.setTitle(title).setMessage(content).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultDialogListener.onResultDialog(which);
                    }
                }).setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultDialogListener.onResultDialog(which);
                    }
                }).create();
                return alertDialog;
            }
        }, cancelable);
        instance.show(fragmentManager, "showDialog");
    }
}
