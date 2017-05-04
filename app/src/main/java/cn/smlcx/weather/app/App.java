package cn.smlcx.weather.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcx on 2017/5/4.
 */

public class App extends Application{
    protected final String TAG = this.getClass().getSimpleName();
    private static App app;
    private List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        LeakCanary.install(this);
    }
    public static App getInstance() {
        return app;
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
