package cn.smlcx.weather.ui.adapter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import cn.smlcx.weather.Base.BaseAdapter;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.ui.activity.DetailActivity;

/**
 * Created by lcx on 2017/5/10.
 */

public class NewsAdapter extends BaseAdapter<NewsBean.ResultBean.DataBean>{

    public NewsAdapter(Context context, List<NewsBean.ResultBean.DataBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, NewsBean.ResultBean.DataBean bean) {
        holder.setImageFromInternet(R.id.c_img,bean.getThumbnail_pic_s());
        holder.setText(R.id.c_title,bean.getTitle());
        holder.setText(R.id.c_date,bean.getDate().split(" ")[0]);
        holder.setText(R.id.c_content,"来源:"+bean.getAuthor_name());
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_choice;
    }

    @Override
    protected void onItemClick(int position) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("url", mBeans.get(position).getUrl());
        intent.putExtra("title", mBeans.get(position).getTitle());
        mContext.startActivity(intent);
    }

}
