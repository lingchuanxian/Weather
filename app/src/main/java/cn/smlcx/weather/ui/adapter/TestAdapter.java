package cn.smlcx.weather.ui.adapter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import cn.smlcx.weather.Base.BaseAdapter;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.ui.activity.DetailActivity;

/**
 * Created by lcx on 2017/5/10.
 */

public class TestAdapter extends BaseAdapter<ChoiceBean.ResultBean.ListBean>{

    public TestAdapter(Context context, List<ChoiceBean.ResultBean.ListBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, ChoiceBean.ResultBean.ListBean bean) {
        holder.setImageFromInternet(R.id.c_img,bean.getFirstImg());
        holder.setText(R.id.c_title,bean.getTitle());
        holder.setText(R.id.c_content,bean.getSource());
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.choice_item;
    }

    @Override
    protected void onItemClick(int position) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("url", mBeans.get(position).getUrl());
        intent.putExtra("title", mBeans.get(position).getTitle());
        mContext.startActivity(intent);
    }

}
