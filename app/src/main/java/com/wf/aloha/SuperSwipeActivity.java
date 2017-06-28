package com.wf.aloha;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
                },2000);
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                gifHeader.onStatePreparing(enable);

            }
        });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("item -- " + i);
        }
        return data;
    }
}
