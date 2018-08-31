package com.yazhi1992.practice.mvp.app;


import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import java.util.HashSet;
import java.util.Set;

/******************************************
 * 类名称：App
 * 类描述：
 *
 * @version: 2.3.1
 * @author: caopeng
 * @time: 2016/9/13 10:53
 ******************************************/
public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;

    public static App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //蒲公英crash上报
//        PgyCrashManager.register(this);
        //初始化内存泄漏检测
//        LeakCanary.install(this);
        //初始化过度绘制检测
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
//        初始化realm
    }

    public void registerActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void unregisterActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    if (act != null && !act.isFinishing())
                        act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}