package com.zbc.tinkerdemo.manager;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.zbc.tinkerdemo.crash.SampleUncaughtExceptionHandler;

import java.io.File;

/**
 * Created by benchengzhou on 2019/3/12  16:24 .
 * 作者邮箱： mappstore@163.com
 * 功能描述： 腾讯热更新tinker的管理类
 * 类    名： TimkerManager
 * 备    注：
 */

public class TinkerManager {

    private static boolean isInstalled = false;
    private static ApplicationLike applicationLike;
    private static SampleUncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * 有外部调用完成tinker的初始化
     *
     * @param appLike
     */
    public static void installTinker(ApplicationLike appLike) {
        applicationLike = appLike;
        if (isInstalled) {
            return;
        }
        //调用tinker进行初始化安装
        TinkerInstaller.install(applicationLike);
        isInstalled = true;
    }


    /**
     * 加载补丁包文件
     *
     * @param path 补丁包文件的制定路径
     */
    public static void loadPatch(String path) {
        //文件存不存在的直接不进行处理,同时不处理文件夹
        if (!new File(path).exists() || new File(path).isDirectory()) {
            return;
        }
        if (!Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }


    /**
     * 清除所有更新包文件
     *
     * @param context
     */
    public static  void cleanPatch(Context context) {
        TinkerInstaller.cleanPatch(context);
    }

    public static void initFastCrashProtect() {
        if (uncaughtExceptionHandler == null) {
            uncaughtExceptionHandler = new SampleUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    }


    public static void setUpgradeRetryEnable(boolean enable) {
        UpgradePatchRetry.getInstance(applicationLike.getApplication()).setRetryEnable(enable);
    }


    /**
     * 获取全局上下文
     *
     * @return
     */
    private static Context getApplicationContext() {
        return applicationLike == null ? null : applicationLike.getApplication().getApplicationContext();
    }


    public static void setTinkerApplicationLike(ApplicationLike appLike) {
        applicationLike = appLike;
    }

    public static ApplicationLike getTinkerApplicationLike() {
        return applicationLike;
    }


}
