package com.wf.aloha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangfei on 2017/10/17.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder> {
    private Context mContext;
    private List<CheckBean> mDatas;
    private CheckItemListener mCheckListener;

    public CheckAdapter(Context mContext, List<CheckBean> mDatas, CheckItemListener mCheckListener) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mCheckListener = mCheckListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.check_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CheckBean bean = mDatas.get(position);
        holder.item_order_tv.setText(bean.getOrder());
        holder.item_name_tv.setText(bean.getName());
        holder.item_content_tv.setText(bean.getContent());
        holder.item_time_tv.setText(bean.getTime());
        holder.item_cb.setChecked(bean.isChecked());
        //点击实现选择功能，当然可以把点击事件放在item_cb对应的CheckBox上，只是焦点范围较小
        holder.item_content_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setChecked(!bean.isChecked());
                holder.item_cb.setChecked(bean.isChecked());
                if (null != mCheckListener) {
                    mCheckListener.itemChecked(bean, holder.item_cb.isChecked());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //序号
        private TextView item_order_tv;
        //选择
        private CheckBox item_cb;
        //整个条目
        private LinearLayout item_content_ll;
        //名称
        TextView item_name_tv;
        //内容
        TextView item_content_tv;
        //时间
        private TextView item_time_tv;


        public ViewHolder(View itemView) {
            super(itemView);
            item_order_tv = (TextView) itemView.findViewById(R.id.item_order_tv);
            item_cb = (CheckBox) itemView.findViewById(R.id.item_cb);
            item_name_tv = (TextView) itemView.findViewById(R.id.item_name_tv);
            item_content_tv = (TextView) itemView.findViewById(R.id.item_content_tv);
            item_time_tv = (TextView) itemView.findViewById(R.id.item_time_tv);
            item_content_ll = (LinearLayout) itemView.findViewById(R.id.item_content_ll);
        }
    }

    public interface CheckItemListener {

        void itemChecked(CheckBean checkBean, boolean isChecked);
    }
}