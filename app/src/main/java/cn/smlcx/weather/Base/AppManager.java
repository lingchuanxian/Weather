package cn.smlcx.weather.Base;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 用于管理所有activity,和在前台的 activity
 * 可以通过直接持有AppManager对象执行对应方法
 * 也可以通过eventbus post事件,远程遥控执行对应方法
 * Created by jess on 14/12/2016 13:50
 * Contact with jess.yan.effort@gmail.com
 */
@Singleton
public class AppManager {
    protected final String TAG = this.getClass().getSimpleName();
    public static final String APPMANAGER_MESSAGE = "appmanager_message";
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_add_activity_list";//是否加入到activity的list，管理
    public static final int START_ACTIVITY = 0;
    public static final int SHOW_SNACKBAR = 1;
    public static final int KILL_ALL = 2;
    public static final int APP_EXIT = 3;
    private Application mApplication;

    //管理所有activity
    public List<Activity> mActivityList;
    //当前在前台的activity
    private Activity mCurrentActivity;

    @Inject
    public AppManager(Application application) {
        this.mApplication = application;
    }



    /**
     * 将在前台的activity保存
     *
     * @param currentActivity
     */
    public void setCurrentActivity(Activity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }

    /**
     * 获得当前在前台的activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 返回一个存储所有未销毁的activity的集合
     *
     * @return
     */
    public List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }


    /**
     * 添加Activity到集合
     */
    public void addActivity(Activity activity) {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        synchronized (AppManager.class) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity);
            }
        }
    }

}
