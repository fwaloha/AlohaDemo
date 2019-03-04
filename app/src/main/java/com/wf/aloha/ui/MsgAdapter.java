package com.wf.aloha.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wf.aloha.R;

import java.util.ArrayList;

class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgHolder> {
    private final ArrayList<Msg> mList;

    public MsgAdapter(ArrayList<Msg> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public MsgHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.msg_layout, viewGroup, false);
        MsgHolder msgHolder = new MsgHolder(rootView);
        return msgHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgHolder msgHolder, int i) {
        Msg item = mList.get(i);
        if(item.getType().contains("left")){
            msgHolder.tvRight.setVisibility(View.GONE);
            msgHolder.tvLeft.setVisibility(View.VISIBLE);
            msgHolder.tvLeft.setText(item.name);
        }else {
            msgHolder.tvLeft.setVisibility(View.GONE);
            msgHolder.tvRight.setVisibility(View.VISIBLE);
            msgHolder.tvRight.setText(item.name);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MsgHolder extends RecyclerView.ViewHolder{
        TextView tvLeft;
        TextView tvRight;

        public MsgHolder(View rootView) {
            super(rootView);
            tvLeft = rootView.findViewById(R.id.tv_left);
            tvRight = rootView.findViewById(R.id.tv_right);
        }
    }
}
