package com.wf.aloha.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import java.io.Serializable;

public interface OnCreateDialog extends Serializable {
    Dialog creatDialog(Context context);
}
