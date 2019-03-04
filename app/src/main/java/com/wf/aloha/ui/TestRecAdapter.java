package com.wf.aloha.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import java.util.ArrayList;

class TestRecAdapter extends RecyclerView.Adapter<TestRecAdapter.RecViewHolder> implements View.OnClickListener {
    private final ArrayList<Fruit> mFruits;
    private OnItemClickListener mListener;

    public TestRecAdapter(ArrayList<Fruit> fruits) {
        this.mFruits = fruits;
    }


    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_adapter_layout, viewGroup, false);
        final RecViewHolder recViewHolder = new RecViewHolder(rootView);

        //touch listener
//        recViewHolder.tvName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showInstance(mFruits.get(recViewHolder.getAdapterPosition()).name);
//            }
//        });
//        recViewHolder.picImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showInstance(mFruits.get(recViewHolder.getAdapterPosition()).imageId+"image id");
//            }
//        });
        rootView.setOnClickListener(this);
        return recViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder recViewHolder, int i) {
        if (i % 2 == 0) {
            recViewHolder.picImage.setImageResource(R.mipmap.apple_pic);
        } else {
            recViewHolder.picImage.setImageResource(R.mipmap.banana_pic);
        }
        recViewHolder.tvName.setText(mFruits.get(i).name);
        recViewHolder.itemView.setTag(mFruits.get(i));
    }


    @Override
    public int getItemCount() {
        return mFruits.size();
    }

    //add lists.
    public void add(ArrayList<Fruit> fruits) {
        mFruits.addAll(fruits);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        ImageView picImage;
        TextView tvName;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            picImage = itemView.findViewById(R.id.iv_img);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }


}
