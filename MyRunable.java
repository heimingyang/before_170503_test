package com.example.before_170503_test;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by 黑明阳 on 2017/4/6.
 *
 * 这是一个自定义类
 * 作用：可以自由在主线程和子线程切换
 *
 */

public class MyRunable {
    private static Handler shandler=new Handler(Looper.getMainLooper());
    public static void mainthread(Runnable myrun){
        shandler.post(myrun);
    }
    public static void childthread(Runnable myrun){
        new Thread(myrun).start();
    }
}
