package cn.smlcx.weather.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Base.BaseRecyclerViewAdapter;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.HttpResult;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerChoiceComponent;
import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.adapter.ChoiceAdapter;

public class ChoiceFragment extends BaseFragment<ChoiceListPresenter> implements ViewContract.ChoiceListView {
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    ChoiceListPresenter mChoiceListPresenter;
    @BindView(R.id.choice_list)
    RecyclerView mChoiceList;
    Unbinder unbinder;
    private List<ChoiceBean> mChoice = new ArrayList<>();

    private BaseRecyclerViewAdapter<ChoiceBean> mAdapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("微信精选");

        DaggerChoiceComponent
                .builder()
                .choiceModule(new ChoiceModule(this))
                .build()
                .inject(this);
        mPresenter.requestChoiceList("d975b5fe029c0691fe5d683cb68b86ac", 1, 20);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mChoiceList.setLayoutManager(linearLayoutManager);
        mAdapter = new ChoiceAdapter(mChoice);
        mChoiceList.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoding(String msg) {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void showErr(String err) {

    }

    @Override
    public void showChoiceList(List<ChoiceBean> result) {
        Log.e(TAG, "showChoiceList的长度为： "+result.size());
        List<ChoiceBean> list2 = fromJsonList(result.toString(),ChoiceBean.class);
        mChoice.addAll(list2);
        Log.e(TAG, "mChoice的长度为： "+mChoice.size());
        mAdapter.notifyDataSetChanged();
    }

    public <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        Gson mGson = new Gson();
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
