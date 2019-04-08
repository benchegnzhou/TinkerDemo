package com.zbc.tinkerdemo.application;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.zbc.tinkerdemo.manager.TinkerManager;
import com.ztsc.commonutils.CommonUtil;
import com.ztsc.commonutils.utilconfig.Config;

/**
 * Created by benchengzhou on 2019/3/12  16:38 .
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： CustomApplicationLike
 * 备    注：
 */
@DefaultLifeCycle(application = ".MApplication"                     //application类名
        , flags = ShareConstants.TINKER_ENABLE_ALL                 //tinkerFlags
        , loadVerifyFlag = false)                                   //tinkerLoadVerifyFlag
public class CustomApplicationLike extends ApplicationLike {


    public static Context sApplication;
    public static CustomApplicationLike sApplike;


    public CustomApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //使应用支持分包
        MultiDex.install(base);


        SampleApplicationContext.application = getApplication();
        SampleApplicationContext.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);

        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        //optional set logIml, or you can use default debug log
        //TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());

        //获取全局上下文
        sApplication = base;
        sApplike = this;


        CommonUtil.getInstance().init(base, new Config()
                .setLogOpen(true)
                .setLogTag("ZBC_TINKER")
                .setToastOpen(true));
    }




    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

}
