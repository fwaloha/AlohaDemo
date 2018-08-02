package com.wf.aloha.utils;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DialogFragmentDes extends DialogFragment {


    private OnCancelListener mOnCancelListener;
    private OnCreateDialog mOnCreateDialog;

    public interface OnCancelListener {
        void onCancel();
    }

//    public interface OnCreatDialog extends Serializable {
//        Dialog creatDialog(Context context);
//    }

    public static DialogFragmentDes getInstance(OnCancelListener onCancelListener, OnCreateDialog onCreatDialog, boolean cancelAble) {
        DialogFragmentDes dialogFragmentDes = new DialogFragmentDes();
        dialogFragmentDes.mOnCancelListener = onCancelListener;
        dialogFragmentDes.mOnCreateDialog = onCreatDialog;
        dialogFragmentDes.setCancelable(cancelAble);
        return dialogFragmentDes;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mOnCreateDialog == null) {
            mOnCreateDialog = (OnCreateDialog) savedInstanceState.getSerializable("dfragment");
        }
        return mOnCreateDialog.creatDialog(getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("dfragment", mOnCreateDialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        mOnCancelListener.onCancel();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (null != dialog) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.2f;
            window.setAttributes(attributes);
        }
    }
}
