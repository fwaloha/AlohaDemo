package com.wf.aloha.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wf.aloha.R;
import com.wf.aloha.utils.DialogCreatHelper;
import com.wf.aloha.utils.DialogFragmentDes;
import com.wf.aloha.utils.ResultDialogListener;
import com.wf.aloha.utils.TempDialogfragment;
import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogFrgmtActivity extends AppCompatActivity {

    @BindView(R.id.bt_normal)
    Button btNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_frgmt);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.bt_normal, R.id.bt_old})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_normal:
                DialogCreatHelper.showDialog(getFragmentManager(), "hehe title", "contentneirongllllllllkkkk", new ResultDialogListener<Integer>() {
                    @Override
                    public void onResultDialog(Integer result) {
                        switch (result) {
                            case DialogInterface.BUTTON_NEGATIVE:
                                ToastUtils.showInstance("点击了 negative");
                                break;
                            case DialogInterface.BUTTON_POSITIVE:
                                ToastUtils.showInstance("点击了 positive");
                                break;
                        }
                    }
                }, new DialogFragmentDes.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        ToastUtils.showInstance("oncancel");
                    }
                }, false);
                break;
            case R.id.bt_old:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Old type").setMessage("zhe shi old yangshi")
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtils.showInstance("hahahahhaahhaha");
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
//                TempDialogfragment tempDialogfragment = new TempDialogfragment();
//                tempDialogfragment.show(getFragmentManager(),"haha");
                break;
        }
    }
}
