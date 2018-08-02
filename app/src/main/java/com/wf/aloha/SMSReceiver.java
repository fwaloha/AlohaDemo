package com.wf.aloha;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;

import com.wf.aloha.ui.ViewStubActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;

/**
 * Created by wangfei on 2017/9/5.
 */

public class SMSReceiver extends BroadcastReceiver {

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (SMS_RECEIVED_ACTION.equals(action)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null && pdus.length > 0) {
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    int length = messages.length;
                    for (int i = 0; i < length; i++) {
                        byte[] pdu = (byte[]) pdus[i];
                        messages[i] = SmsMessage.createFromPdu(pdu);
                    }
                    for (SmsMessage msg : messages) {
                        // 获取短信内容
                        String content = msg.getMessageBody();
                        String sender = msg.getOriginatingAddress();
                        // 拦截10086的短信
                        if ("10086".equals(sender)) {
                            if (!TextUtils.isEmpty(content)) {
                                String msgPassword = getNumbers(content);
                                if (!TextUtils.isEmpty(msgPassword) && isNumeric(msgPassword)) {
                                    //收到验证码后，启动APP，并把验证码传递到页面
                                    Intent newIntent = new Intent(context, ViewStubActivity.class);
                                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 注意，必须添加这个标记，否则启动会失败
                                    newIntent.putExtra("msg", msgPassword);
                                    context.startActivity(newIntent);
                                    break;
                                }
                            }
                            // 对于特定的内容,取消广播
                            abortBroadcast();
                        }
                    }
                }
            }
        }
    }

    /**
     * 截取数字
     *
     * @param content
     * @return
     */
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * <判断字符串是否是数字>
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
