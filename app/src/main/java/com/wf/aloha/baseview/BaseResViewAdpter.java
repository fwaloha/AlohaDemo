package com.wf.aloha.baseview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wf.aloha.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfei on 2017/2/6.
 */
public abstract class BaseResViewAdpter<T> extends RecyclerView.Adapter implements View.OnClickListener {
    private static final int ITEM_FOOTVIEW = 100;
    private final ArrayList<T> mList;
    public static boolean isShowFootView = true;
    private OnItemClickListener mListener;

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    public void setOnItemclickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public BaseResViewAdpter(ArrayList<T> list) {
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == ITEM_FOOTVIEW) {
            View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_more, viewGroup, false);
            FootViewHolder footViewHolder = new FootViewHolder(rootView);
            return footViewHolder;
        }
        return doCreateViewHolder(viewGroup, i);
    }

    protected abstract RecyclerView.ViewHolder doCreateViewHolder(ViewGroup viewGroup, int i);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        doBindViewHolder(mList,viewHolder, i);
        viewHolder.itemView.setOnClickListener(this);
    }

    protected abstract void doBindViewHolder(ArrayList<T> mList, RecyclerView.ViewHolder viewHolder, int i);

    @Override
    public int getItemCount() {
        return isShowFootView ? mList.size() + 1 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowFootView && getItemCount() == position + 1) {
            return ITEM_FOOTVIEW;
        }
        return doGetItemViewType(position);
    }

    protected abstract int doGetItemViewType(int position);


    public class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setShowFootView(boolean show) {
        isShowFootView = show;
        notifyDataSetChanged();
    }
    
    public boolean isShowFootView(){
        return isShowFootView;
    }
    
    public void initData(List list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
