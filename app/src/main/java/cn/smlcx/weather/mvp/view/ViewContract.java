package cn.smlcx.weather.mvp.view;

import cn.smlcx.weather.Base.BaseView;

/**
 * Created by lcx on 2017/5/5.
 */

public interface ViewContract {
    interface ChoiceListView extends BaseView{
        void showChoiceList();
    }
}
