package cn.smlcx.weather.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.WeatherBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerWeatherComponent;
import cn.smlcx.weather.di.module.WeatherModule;
import cn.smlcx.weather.mvp.presenter.WeatherPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.adapter.WeatherAdapter;


public class WeatherFragment extends BaseFragment<WeatherPresenter> implements ViewContract.WeatherView {
    protected final String TAG = this.getClass().getSimpleName();
    public LocationClient mLocationClient = null;
    @BindView(R.id.curLocation)
    TextView mCurLocation;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.temperature)
    TextView mTemperature;
    @BindView(R.id.weather)
    TextView mWeather;
    @BindView(R.id.weather_list)
    RecyclerView mWeatherList;
    private WeatherBean mData = new WeatherBean();
    private WeatherAdapter mAdapter;
    private List<WeatherBean.ResultBean> mDatas = new ArrayList<WeatherBean.ResultBean>();
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("最新天气");
        mLocationClient = new LocationClient(getActivity());
        //声明LocationClient类
        mLocationClient.registerLocationListener(mListener);
        //注册监听函数
        initLocation();
        mLocationClient.start();
        showLoding();
        initRecycleView();
        mAdapter = new WeatherAdapter(getActivity(),mDatas);
        mWeatherList.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector() {
        DaggerWeatherComponent
                .builder()
                .weatherModule(new WeatherModule(this))
                .build()
                .inject(this);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
            }
            Log.e(TAG, "onReceiveLocation: " + sb.toString());
            getWeather(location);
            mLocationClient.stop();
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

    @Override
    public void showWeather(WeatherBean bean) {
        WeatherBean.ResultBean today = bean.getResult().get(0);
        mCurLocation.setText(today.getCitynm());
        mDate.setText(today.getDays() + " " + today.getWeek());
        mTemperature.setText(today.getTemperature());
        mWeather.setText(today.getWeather());
        bean.getResult().remove(0);
        mDatas.clear();
        mDatas.addAll(bean.getResult());
        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
        hideLoding();
    }

    private void getWeather(BDLocation location) {
        String city = "";
        if (location.getCity().contains("市") || location.getCity().contains("省")) {
            city = location.getCity().substring(0, location.getCity().length() - 1);
        }
        mPresenter.requestWeather(city);
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mWeatherList.setLayoutManager(linearLayoutManager);
    }

}
