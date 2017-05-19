package cn.smlcx.weather.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.smlcx.weather.R;

/**
 * Created by lcx on 2017/5/10.
 */

public class ContactAdapter extends BaseQuickAdapter<String> {
    private Context mContext;
    public ContactAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }
    public ContactAdapter(List<String> data) {
        super(data);
    }
    public ContactAdapter(View contentView, List<String> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.contact_userName,item);
    }
}
