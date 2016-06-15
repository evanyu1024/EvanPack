package com.evan.demo.utils.manage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

/**
 * Crash帮助类,用于记录当程序Crash时的Crash记录
 * Created by evanyu on 16/6/12.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler mInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultHandler; // 默认的异常处理类
    private Context mContext;

    // 私有化构造方法
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    /**
     * 当系统中出现未被捕获的异常,系统将会自动调用该方法
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            String crashInfo = getCrashInfo();
            // 将异常信息写到SD卡上
            // writeExceptionToSDCard(ex, crashInfo);
            // 将异常信息上传至服务器
            // uploadExceptionToServer(ex, crashInfo);

            // 出现没有处理的异常时就默认杀死当前进程
            Process.killProcess(Process.myPid());
        }
    }

    /**
     * 将异常信息写到SD卡上
     */
    private void writeExceptionToSDCard(Throwable ex, String crashInfo) {

    }

    /**
     * 将异常信息上传至服务器
     */
    public void uploadExceptionToServer(Throwable ex, String crashInfo) {

    }

    public String getCrashInfo() {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // APP版本号
        String version = packageInfo.versionName;
        int versionCode = packageInfo.versionCode;

        // Android系统版本
        String osVersion = Build.VERSION.RELEASE;
        int osVersionCode = Build.VERSION.SDK_INT;

        // 手机制造商
        String vendor = Build.MANUFACTURER;

        // 手机型号
        String model = Build.MODEL;

        // CPU架构
        String cpuABI = Build.CPU_ABI;

        return null; // 还没有完善
    }
}
