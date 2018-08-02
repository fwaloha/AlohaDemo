package com.wf.aloha.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class MyDialog extends AlertDialog implements Serializable {
    public MyDialog(@NonNull Context context) {
        super(context);
    }
}
