package com.hee1kang.klib;

import android.util.Log;

/**
 * Created by Eric on 2017-05-30.
 */

public class Tog {

    public static final String TAG = "#{V" + BuildConfig.VERSION_CODE +"}";
    public static boolean ISSHOW = true;
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
        if(!ISSHOW)
            return;
        Log.e(TAG+tag ,""+msg);
    }

    public static void te(String msg)
    {
        if(!ISSHOW)
            return;

        Log.e(TAG,""+msg);
    }

    public static void ti(String tag, String msg)
    {
        if(!ISSHOW)
            return;
        Log.i(TAG+tag,""+msg);
    }

    public static void ti(String msg)
    {
        if(!ISSHOW)
            return;

        Log.i(TAG ,""+msg);
    }




    public static void e(String tag, String msg)
    {
        if(!ISSHOW)
            return;
        Log.e(TAG+tag ,""+msg);
    }

    public static void e(String msg)
    {
        if(!ISSHOW)
            return;

        Log.e(TAG,""+msg);
    }
    public static void i(String tag, String msg)
    {
        if(!ISSHOW)
            return;
        Log.i(TAG+tag,""+msg);
    }

    public static void i(String msg)
    {
        if(!ISSHOW)
            return;

        Log.i(TAG,""+msg);
    }
    public static void d(String tag, String msg)
    {
        if(!ISSHOW)
            return;
        Log.d(TAG+tag ,""+msg);
    }

    public static void d(String msg)
    {
        if(!ISSHOW)
            return;

        Log.d(TAG,""+msg);
    }
    public static void w(String tag, String msg)
    {
        if(!ISSHOW)
            return;
        Log.w(TAG+tag ,""+msg);
    }

    public static void w(String msg)
    {
        if(!ISSHOW)
            return;

        Log.w(TAG,""+msg);
    }
}
