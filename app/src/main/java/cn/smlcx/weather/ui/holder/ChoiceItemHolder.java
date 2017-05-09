package cn.smlcx.weather.ui.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import cn.smlcx.weather.Base.BaseViewHolder;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.R;
import rx.Observable;


/**
 * Created by jess on 9/4/16 12:56
 * Contact with jess.yan.effort@gmail.com
 */
public class ChoiceItemHolder extends BaseViewHolder<ChoiceBean.ResultBean.ListBean> {

    @Nullable
    @BindView(R.id.c_img)
    ImageView mCImg;
    @Nullable
    @BindView(R.id.c_title)
    TextView mCTitle;
    @Nullable
    @BindView(R.id.c_content)
    TextView mCContent;

   private Context mContext;

    public ChoiceItemHolder(View itemView) {
        super(itemView);
        Log.e("viewholder","进入ViewHolder");
        mContext = itemView.getContext();
    }

    @Override
    public void setData(ChoiceBean.ResultBean.ListBean data, int position) {
        Log.e("viewholder","进入setData");
        Observable.just(data.getTitle())
                .subscribe(RxTextView.text(mCTitle));

        Observable.just("来源:"+data.getSource())
                .subscribe(RxTextView.text(mCContent));
        Log.e(TAG, "setData: "+mContext.toString() );

        Glide.with(mContext)
                .load(data.getFirstImg())
                .into(mCImg);
    }
    @Override
    protected void onRelease() {
    }
}
