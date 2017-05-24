package cn.smlcx.weather.app;

import android.app.Activity;
import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lcx on 2017/5/4.
 */

public class App extends Application{
    protected final String TAG = this.getClass().getSimpleName();
    private static App instance;
    private List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LeakCanary.install(this);
        /*DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build()
                .app();*/
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //设置自动登录
        options.setAutoLogin(false);
        //初始化
        EMClient.getInstance().init(instance, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
    public static App getInstance() {
        return instance;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public void finishActivity(){
        for(Activity activity:activityList){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
