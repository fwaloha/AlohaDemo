package com.wf.aloha;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wf.aloha.bean.CryptoObjectHelper;
import com.wf.aloha.bean.FingerPrinterCallback;
import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FingerActivity extends AppCompatActivity {

    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.iv_finger)
    ImageView ivFinger;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    private FingerprintManagerCompat mFingerprintManager;

    public static final int MSG_AUTH_SUCCESS = 100;
    public static final int MSG_AUTH_FAILED = 101;
    public static final int MSG_AUTH_ERROR = 102;
    public static final int MSG_AUTH_HELP = 103;
    private Handler handler;
    private CryptoObjectHelper cryptoObjectHelper;
    private CancellationSignal cancellationSignal;
    private FingerPrinterCallback fingerPrinterCallback;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);
        ButterKnife.bind(this);

        getFingerManager();
//        检查是否有录入的指纹
        if (!mFingerprintManager.hasEnrolledFingerprints()) {
            ToastUtils.showInstance("没有录入的指纹");
        }

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_AUTH_SUCCESS:
                        tvTips.setText("Fingerprint recognized.");
                        break;
                    case MSG_AUTH_FAILED:
                        tvTips.setText("Fingerprint failed.");

                        break;
                    case MSG_AUTH_ERROR:
                        tvTips.setText("Fingerprint error.");

                        break;
                    case MSG_AUTH_HELP:
                        break;
                }
            }
        };


        try {
            cryptoObjectHelper = new CryptoObjectHelper();
            cancellationSignal = new CancellationSignal();
            fingerPrinterCallback = new FingerPrinterCallback(handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getFingerManager() {
        mFingerprintManager = FingerprintManagerCompat.from(this);
        if (!mFingerprintManager.isHardwareDetected()) {
            ToastUtils.showInstance("no fingerprint device");
        }
//        check whether phone is secure
        KeyguardManager keygardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (keygardManager.isKeyguardSecure()) {
            ToastUtils.showInstance("设置过密码了");
        } else {
            ToastUtils.showInstance("未设置密码");
        }
    }

    @OnClick({R.id.bt_start, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                try {
                    mFingerprintManager.authenticate(cryptoObjectHelper.buildCryptoObject(), 0, cancellationSignal, fingerPrinterCallback, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.bt_cancel:
                break;
        }
    }
}
