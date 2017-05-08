package cn.smlcx.weather.ui.holder;

import android.util.Log;
import android.view.View;

import cn.smlcx.weather.Base.BaseViewHolder;
import cn.smlcx.weather.Bean.ChoiceBean;

/**
 * Created by jess on 9/4/16 12:56
 * Contact with jess.yan.effort@gmail.com
 */
public class ChoiceItemHolder extends BaseViewHolder<ChoiceBean> {
/*

    @BindView(R.id.c_img)
    ImageView mCImg;
    @BindView(R.id.c_title)
    TextView mCTitle;
    @BindView(R.id.c_content)
    TextView mCContent;
*/

    public ChoiceItemHolder(View itemView) {
        super(itemView);
        Log.e("viewholder","进入ViewHolder");
    }

    @Override
    public void setData(ChoiceBean data, int position) {
        Log.e("viewholder","进入setData");
       // mCTitle.setText(data.getTitle());
       // mCContent.setText(data.getMark());
    }
}
