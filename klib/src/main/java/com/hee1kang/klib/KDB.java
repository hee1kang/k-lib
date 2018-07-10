package com.hee1kang.klib;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class KDB {

    private static final String DB_NAME = "kdb_preferences";

    private static Gson GSON;
    private static KDB Instance = null;

    private SharedPreferences sp;

    public static KDB getInstance(Context context)
    {
        if(Instance == null)
        {
            Instance = new KDB(context);
        }

        return Instance;
    }

    private KDB(Context context)
    {
        sp = getPer(context);
        GSON = new Gson();
    }

    private SharedPreferences getPer(Context context)
    {
        return context.getApplicationContext().getSharedPreferences(DB_NAME, 0);
    }

    public String getString(String key)
    {
        return sp.getString(key,"");
    }

    public void Assert(String key, Object obj)
    {
//        if (obj == null) {
//            throw new IllegalArgumentException("object is null");
//        }
//
//        if (key.equals("") || key == null) {
//            throw new IllegalArgumentException("key is empty or null");
//        }
    }

    public void putString(String key, String value)
    {
        Assert(key,value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putObject(String key, Object object)
    {
        Assert(key,object);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, GSON.toJson(object));
        editor.apply();
    }

    public <T> T getObject(String key, Type a) {
        String gson = sp.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                return null;
//                throw new IllegalArgumentException("Object with key " + key + " is instanceof other class");
            }
        }
    }

    public <T> T getObject(String key, Class<T> a) {

        String gson = sp.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                return null;
//                throw new IllegalArgumentException("Object with key " + key + " is instanceof other class");
            }
        }
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).apply();
    }

    public int getInt(String key)
    {
        return sp.getInt(key,0);
    }

    public void putInt(String key, int value)
    {
        Assert(key,value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public long getLong(String key)
    {
        return sp.getLong(key,0);
    }

    public void putLong(String key, long value)
    {
        Assert(key,value);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

}