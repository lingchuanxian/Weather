package cn.smlcx.weather.ui.adapter;

import android.content.Context;

import java.util.List;

import cn.smlcx.weather.Base.BaseAdapter;
import cn.smlcx.weather.Bean.WeatherBean;
import cn.smlcx.weather.R;

/**
 * Created by lcx on 2017/5/10.
 */

public class WeatherAdapter extends BaseAdapter<WeatherBean.ResultBean>{

    public WeatherAdapter(Context context, List<WeatherBean.ResultBean> beans) {
        super(context, beans);
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, WeatherBean.ResultBean bean) {
        holder.setText(R.id.future_date,bean.getWeek()+"("+bean.getDays()+")");
        holder.setText(R.id.future_temp,bean.getTemperature());
        holder.setText(R.id.future_weather,bean.getWeather());
        holder.setImageFromInternet(R.id.future_img01,bean.getWeather_icon());
        holder.setImageFromInternet(R.id.future_img02,bean.getWeather_icon1());
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.weather_item;
    }

}
