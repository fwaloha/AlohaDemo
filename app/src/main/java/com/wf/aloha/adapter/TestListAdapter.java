package com.wf.aloha.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wf.aloha.R;
import com.wf.aloha.ui.Fruit;

import java.util.List;

public class TestListAdapter extends ArrayAdapter<Fruit> {
    private final List<Fruit> mLists;
    private final int resouceId;

    public TestListAdapter(@NonNull Context context, int resource, List<Fruit> lists) {
        super(context, resource,lists);
        this.mLists = lists;
        this.resouceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView;
        Viewholder holder;

        if(convertView!=null){
            rootView = convertView;
            holder = (Viewholder) rootView.getTag();
        }else {
            rootView = LayoutInflater.from(getContext()).inflate(resouceId, parent, false);
            
            holder = new Viewholder();
            holder.image = rootView.findViewById(R.id.iv_img);
            holder.tvName = rootView.findViewById(R.id.tv_name);
            rootView.setTag(holder);
        }
        Fruit item = getItem(position);
        if(position%2==0){
            holder.image.setImageResource(R.mipmap.banana_pic);
        }else {
            holder.image.setImageResource(R.mipmap.apple_pic);
        }
        holder.tvName.setText(item.name);
        return rootView;
    }

    @Override
    public int getCount() {
        return mLists.size();
//        return super.getCount();
    }

    @Nullable
    @Override
    public Fruit getItem(int position) {
        return mLists.get(position);
//        return super.getItem(position);
    }

    private class Viewholder {
        ImageView image;
        TextView tvName;
    }
}
