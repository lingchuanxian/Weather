/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.weather.ui.adapter;

/**
 * Created by lcx on 2017/4/1.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.R;

/**
 * RecyclerView适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
    protected final String TAG = this.getClass().getSimpleName();
    private List<ChoiceBean> mDataList;

    public MyAdapter(List<ChoiceBean> list) {
        mDataList = list;
    }

    @Override
    public int getItemCount() {
        // 返回数据集合大小
        return mDataList == null ? 0 : mDataList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "adapter: "+"进入onBindViewHolder");
        holder.mTitle.setText(mDataList.get(position).getTitle());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice_item, parent, false);
        ViewHolder viewholder = new ViewHolder(view);
        view.setOnClickListener(this);
        viewholder.setIsRecyclable(false);
        return new ViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        Log.d("adapter", "onClick:lll ");
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.c_title);
        }
    }
}
