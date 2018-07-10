package com.hee1kang.klib;

import android.util.Log;

public class Tog {

    public static final String TAG = "#{V" + BuildConfig.VERSION_CODE +"}";
    public static boolean SHOW = true;


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
