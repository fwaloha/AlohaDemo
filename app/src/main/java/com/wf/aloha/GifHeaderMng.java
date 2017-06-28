package com.wf.aloha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by wangfei on 2017/6/27.
 */

class GifHeaderMng {
    private final Context mContext;
    private ImageView gif1;
    private ImageView gif2;
    private TextView headerHint;
    private boolean isPull;

    public GifHeaderMng(Context context) {
        this.mContext = context;
        getHeadview();
    }

    public View getHeadview() {
        View headerView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_head, null);
        gif1 = (ImageView) headerView.findViewById(R.id.gif1);
        gif2 = (ImageView) headerView.findViewById(R.id.gif2);
        headerHint = (TextView) headerView.findViewById(R.id.gif_header_hint);
        return headerView;
    }

    public void onStatePreparing(boolean enable) {
        headerHint.setText(enable ? "松开刷新" : "下拉刷新");
        if (!isPull) {
            gif1.setVisibility(View.VISIBLE);
            gif2.setVisibility(View.GONE);
            Glide.with(mContext).load(R.raw.vertical).into(gif1);
            isPull = true;
        }
    }

    public void onStateRefreshing() {
        gif1.setVisibility(View.GONE);
        gif2.setVisibility(View.VISIBLE);
        Glide.with(mContext).load(R.raw.horizontal).into(gif2);
        headerHint.setText("正在刷新...");
        isPull = false;
    }

    public void onStateComplete() {
        
    }
}
