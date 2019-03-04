package com.wf.aloha.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wf.aloha.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgListActivity extends AppCompatActivity {

    @BindView(R.id.rec_view)
    RecyclerView recView;
    @BindView(R.id.bt_left)
    Button btLeft;
    @BindView(R.id.et_left)
    EditText etLeft;
    @BindView(R.id.bt_right)
    Button btRight;
    @BindView(R.id.et_right)
    EditText etRight;
    private ArrayList<Msg> mMsglist;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_list);
        ButterKnife.bind(this);

        initRec();
    }

    private void initRec() {
        mMsglist = new ArrayList<Msg>();
        msgAdapter = new MsgAdapter(mMsglist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);
        recView.setAdapter(msgAdapter);
    }

    @OnClick({R.id.bt_left, R.id.bt_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_left:
                String leftStr = etLeft.getText().toString().trim();
                if(!TextUtils.isEmpty(leftStr)){
                    mMsglist.add(new Msg(leftStr,"left"));
                    msgAdapter.notifyItemInserted(mMsglist.size()-1);
                    recView.smoothScrollToPosition(mMsglist.size()-1);
                    etLeft.setText("");
                }
                break;
            case R.id.bt_right:
                String rightStr = etRight.getText().toString().trim();
                if(!TextUtils.isEmpty(rightStr)){
                    mMsglist.add(new Msg(rightStr,"right"));
                    msgAdapter.notifyItemInserted(mMsglist.size()-1);
                    recView.smoothScrollToPosition(mMsglist.size()-1);
                    etRight.setText("");
                }
                break;
        }
    }
}
