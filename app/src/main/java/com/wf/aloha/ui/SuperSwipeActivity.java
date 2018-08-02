package com.wf.aloha.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wf.aloha.GifHeaderMng;
import com.wf.aloha.R;
import com.wf.aloha.baseview.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuperSwipeActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    ListView recyclerview;
    @BindView(R.id.super_swipe)
    SuperSwipeRefreshLayout superSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_swipe);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        recyclerview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));

        superSwipe.setHeaderViewBackgroundColor(0xfff3f3f3);
        final GifHeaderMng gifHeader = new GifHeaderMng(this);
        superSwipe.setHeaderView(gifHeader.getHeadview());
//        swipeRefreshLayout.setFooterView(createFooterView());

        superSwipe.setTargetScrollWithLayout(true);
        superSwipe.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                gifHeader.onStateRefreshing();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        superSwipe.setRefreshing(false);
                        gifHeader.onStateComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                gifHeader.onStatePreparing(enable);

            }
        });

//        swipeRefreshLayout
//                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
//
//                    @Override
//                    public void onLoadMore() {
//                        footerTextView.setText("正在加载...");
//                        footerImageView.setVisibility(View.GONE);
//                        footerProgressBar.setVisibility(View.VISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                footerImageView.setVisibility(View.VISIBLE);
//                                footerProgressBar.setVisibility(View.GONE);
//                                swipeRefreshLayout.setLoadMore(false);
//                            }
//                        }, 5000);
//                    }
//
//                    @Override
//                    public void onPushEnable(boolean enable) {
//                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
//                        footerImageView.setVisibility(View.VISIBLE);
//                        footerImageView.setRotation(enable ? 0 : 180);
//                    }
//
//                    @Override
//                    public void onPushDistance(int distance) {
//                        // TODO Auto-generated method stub
//
//                    }
//
//                });
    }

//    private View createFooterView() {
//        View footerView = LayoutInflater.from(swipeRefreshLayout.getContext())
//                .inflate(R.layout.layout_footer, null);
//        footerProgressBar = (ProgressBar) footerView
//                .findViewById(R.id.footer_pb_view);
//        footerImageView = (ImageView) footerView
//                .findViewById(R.id.footer_image_view);
//        footerTextView = (TextView) footerView
//                .findViewById(R.id.footer_text_view);
//        footerProgressBar.setVisibility(View.GONE);
//        footerImageView.setVisibility(View.VISIBLE);
//        footerImageView.setImageResource(R.drawable.down_arrow);
//        footerTextView.setText("上拉加载更多...");
//        return footerView;
//    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("item -- " + i);
        }
        return data;
    }
}
