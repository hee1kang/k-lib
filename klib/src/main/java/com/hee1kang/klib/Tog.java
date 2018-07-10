package com.hee1kang.klib;

import android.content.Context;
import android.util.Log;

import com.hee1kang.klib.BuildConfig;

public class Tog {

    public static int versionCode = 1;
    private static String TAG = "#{V" + versionCode +"}";
    public static boolean SHOW = true;

    public static void init(Context context)
    {
        Object object = Util.getBuildConfigValue(context,"VERSION_CODE");
        if(object != null)
        {
            setVersionCode((int)object);
        }
    }

    public static void setVersionCode(int val)
    {
        versionCode = val;
        TAG = "#{V" + versionCode +"}";
    }

    public static void setLogShow(boolean val)
    {
        SHOW = val;
    }

    public static void onE(Throwable e)
    {
        onE(TAG, e);
    }

    public static void onE(String tag, Throwable e)
    {
        Log.e(tag, "" + (e != null ? e.getMessage() : "Throwable null"));
    }

    public static void te(String tag, String msg)
    {
        if(!SHOW)
            return;
        Log.e(TAG+tag ,""+msg);
    }

    public static void te(String msg)
    {
        if(!SHOW)
            return;

        Log.e(TAG,""+msg);
    }

    public static void ti(String tag, String msg)
    {
        if(!SHOW)
            return;
        Log.i(TAG+tag,""+msg);
    }

    public static void ti(String msg)
    {
        if(!SHOW)
            return;

        Log.i(TAG ,""+msg);
    }




    public static void e(String tag, String msg)
    {
        if(!SHOW)
            return;
        Log.e(TAG+tag ,""+msg);
    }

    public static void e(String msg)
    {
        if(!SHOW)
            return;

        Log.e(TAG,""+msg);
    }
    public static void i(String tag, String msg)
    {
        if(!SHOW)
            return;
        Log.i(TAG+tag,""+msg);
    }

    public static void i(String msg)
    {
        if(!SHOW)
            return;

        Log.i(TAG,""+msg);
    }
    public static void d(String tag, String msg)
    {
        if(!SHOW)
            return;
        Log.d(TAG+tag ,""+msg);
    }

    public static void d(String msg)
    {
        if(!SHOW)
            return;

        Log.d(TAG,""+msg);
    }
    public static void w(String tag, String msg)
    {
        if(!SHOW)
            return;
        Log.w(TAG+tag ,""+msg);
    }

    public static void w(String msg)
    {
        if(!SHOW)
            return;

        Log.w(TAG,""+msg);
    }
}
