package cn.smlcx.weather.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.smlcx.weather.utils.KnifeUtil;

/**
 * Created by lcx on 2017/5/4
 * 万能的Viewholder
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener{
    protected OnViewClickListener mOnViewClickListener = null;
    protected final String TAG = this.getClass().getSimpleName();
    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);//点击事件
        //AutoUtils.autoSize(itemView);//适配
        KnifeUtil.bindTarget(this, itemView);//绑定
    }
    /**
     * 设置数据
     * 刷新界面
     *
     * @param
     * @param position
     */
    public abstract void setData(T data, int position);


    /**
     * 释放资源
     */
    protected void onRelease(){

    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }
}
