package cn.smlcx.weather.Base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.smlcx.weather.R;
import cn.smlcx.weather.utils.ScreenUtils;

/**
 * Created by lcx on 2017/5/10.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.CommonViewHolder> {
    protected List<T> mBeans;
    protected Context mContext;
    protected boolean mAnimateItems = true;
    protected int mLastAnimatedPosition = -1;
    private static final int TYPE_ITEM = 0;//普通布局
    private static final int TYPE_FOOTER = 1;//底部加载布局
    public BaseAdapter(Context context, List<T> beans) {
        mContext = context;
        mBeans = beans;
    }
    @Override
    public BaseAdapter.CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view;
        //进行判断显示类型，来创建返回不同的View
        if(viewType==TYPE_ITEM){
            view=mInflater.inflate(getItemLayoutID(viewType),parent,false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            CommonViewHolder itemViewHolder=new CommonViewHolder(view);
            return itemViewHolder;
        }else if(viewType==TYPE_FOOTER){
            view=mInflater.inflate(R.layout.item_foot,parent,false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            FootViewHolder footViewHolder=new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    /**
     * 底部FootView布局
     */
    public class FootViewHolder extends BaseAdapter.CommonViewHolder{
        public FootViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseAdapter.CommonViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        if(position<getItemCount()-1){
            onBindDataToView(holder, mBeans.get(position));
        }
    }

    /**
     * 绑定数据到Item的控件中去
     * @param holder
     * @param bean
     */
    protected abstract void onBindDataToView(CommonViewHolder holder, T bean);
    /**
     * 取得ItemView的布局文件
     */
    public abstract int getItemLayoutID(int viewType);

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mBeans.size() == 0 ? 0 : mBeans.size() + 1;
    }

    public void add(T bean) {
        mBeans.add(bean);
        notifyDataSetChanged();
    }

    public void addAll(List<T> beans) {
        mBeans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clear() {
        mBeans.clear();
        notifyDataSetChanged();
    }
    /**
     item的加载动画
     @param view
     @param position
     */
    private void runEnterAnimation(View view, int position) {
        if (!mAnimateItems) {
            return;
        }
        if (position > mLastAnimatedPosition) {
            mLastAnimatedPosition = position;
            view.setTranslationX(ScreenUtils.getScreenWidth(mContext));
            view.animate()
                    .translationX(0)
                    .setStartDelay(100)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(500)
                    .start();
        }
    }
    public  class CommonViewHolder extends
            RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;
        private View itemView;
        public CommonViewHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
            this.itemView = itemView;
            //添加Item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    onLongItemClick(getAdapterPosition());
                    return true;
                }
            });
        }
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
        public void setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }

        /**
         * 加载drawable中的图片
         */
        public void setImage(int viewId, int resId) {
            ImageView iv = getView(viewId);
            iv.setImageResource(resId);
        }

        /**
         * 加载网络上的图片
         * @param viewId
         * @param url
         */
        public void setImageFromInternet(int viewId, String url) {
            ImageView iv =(ImageView) itemView.findViewById(viewId);
            Glide.with(mContext)
                    .load(url)
                    .into(iv);
            //SolidHttpUtils.getInstance().loadImage(url, iv);//这里可根据自己的需要变更
        }
    }

    /**
     * ItemView的单击事件(如果需要，重写此方法就行)
     * @param position
     */
    protected void onItemClick(int position) {}

    /**
     * ItemView的单击事件(如果需要，重写此方法就行)
     * @param position
     */
    protected void onLongItemClick(int position) {}
}
