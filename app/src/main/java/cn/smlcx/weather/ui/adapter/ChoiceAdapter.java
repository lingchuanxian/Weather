package cn.smlcx.weather.ui.adapter;

import android.util.Log;
import android.view.View;

import java.util.List;

import cn.smlcx.weather.Base.BaseRecyclerViewAdapter;
import cn.smlcx.weather.Base.BaseViewHolder;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.ui.holder.ChoiceItemHolder;

/**
 * Created by lcx on 2017/5/6 12:57
 * Contact with jess.yan.effort@gmail.com
 */
public class ChoiceAdapter extends BaseRecyclerViewAdapter<ChoiceBean> {

    public ChoiceAdapter(List<ChoiceBean> infos) {
        super(infos);
        Log.e("adapter","进入适配器");
    }

    @Override
    public BaseViewHolder<ChoiceBean> getHolder(View v, int viewType) {
        Log.e("adapter","进入getHolder");
        return new ChoiceItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.choice_item;
    }
}
