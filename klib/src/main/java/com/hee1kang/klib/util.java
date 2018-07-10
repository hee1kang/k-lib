package com.hee1kang.klib;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import org.joda.time.DateTime;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * Created by Eric on 2018-07-10.
 */

public class util {

    public static final String N = "0";
    public static final String Y = "1";

    public static boolean isAppOnForeground(Context context, String appPackageName) {

        if(context != null) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
//        Tog.te("####","isAppOnForeground " + (appProcesses != null));
            if(appProcesses == null) {
                return false;
            }
            final String packageName = appPackageName;
            for(ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
//            Tog.te("####","isAppOnForeground " + appProcess.processName +  " // " + appProcess.importance);
                if(appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verifyPackage(Context context, String packageName) {
        boolean installed = false;

        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, 0);
            installed = true;
            Tog.te("####","verifyPackage OK " + packageName);
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
            Tog.te("####","verifyPackage fail " + packageName);
        }
        return installed;
    }

    public static void installPlayService(final Activity context) {
        try {
            new AlertDialog.Builder(context).setTitle("Google Play Services").setMessage("Required Google Play Services.").setCancelable(false).setPositiveButton("Install", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    try {
                        Tog.te("####", "installPlayService 2");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.gms"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.android.vending");
                        context.startActivity(intent);
                    } // 마켓 앱이 없다면 마켓 웹 페이지로 이동시켜주도록한다. // (이 상태에서는 설치하기 힘들겠지만은....)
                    catch(ActivityNotFoundException f) {
                        Tog.te("####", "installPlayService 3");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.gms"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            }).create().show();
        } catch(Exception e) { e.printStackTrace(); }
    }


//    @WorkerThread
//    public static void cleanCache(Context context) {
//        long date = IODB.getInstance().getLong(G.DB.DB_KEY_CACHE_DATE);
//        long now = DateTime.now().getMillis();
//        if(date == 0 || ((now - date) > (1000 * 60 * 60 * 72)))
//        {
//            final File cache = new File(getCacheDir(context));
//            deleteFile(cache , ImageTool.TEMP_FILE_NAME_PRE);
//            IODB.getInstance().putLong(G.DB.DB_KEY_CACHE_DATE, now);
//        }
//
//    }

    public static void deleteFile(File dir, String keyword) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();

            for (File file : files) {
                if (null != file && file.isFile()) {

                    if(keyword != null)
                    {
                        if(file.getName().indexOf(keyword) > -1)
                        {
                            file.delete();
                            continue;
                        }
                    }

               /*     long lastModified = file.lastModified();
                    if (0 < lastModified) {
                        Date lastMDate = new Date(lastModified);
                        Date today = new Date(System.currentTimeMillis());
                        if (null != lastMDate && null != today) {
                            long diff = today.getTime() - lastMDate.getTime();
                            long diffDays = diff / (24 * 60 * 60 * 1000);
                            if (day < diffDays) {
                                file.delete();
                            }
                        }
                    }*/

                }
            }
        }
    }

    public static File deleteFile(String path) {
        File file = new File(path);
        if (file != null && file.exists()) {
            file.delete();
        }
        return file;
    }

    public static String getCacheDir(Context context) {
        String dir;
        File file = context.getExternalCacheDir();
        if(file == null)
        {
            file = Environment.getExternalStorageDirectory();
            if(file == null)
            {
                dir =  "/storage/emulated/0/Android/data/"+ BuildConfig.APPLICATION_ID+"/cache";
            }
            else
            {
                dir = file.getPath() + "/Android/data/"+ BuildConfig.APPLICATION_ID+"/cache";
            }
        }
        else
        {
            dir = file.getPath();
        }

        file = new File(dir);
        if (file != null && !file.exists())
            file.mkdirs();
        dir = file.getPath() + "/";

        return dir;
    }

    public static String getStrCommaNumber(String number)
    {
        if(TextUtils.isEmpty(number))
            return "";
        return getStrCommaNumber(Integer.parseInt(number));
    }

    public static String getStrCommaNumber(int number)
    {
        return String.format(Locale.getDefault(), "%,d", number);
    }




    public static void CallPhoneDial(Context context, String phone) {
        if(TextUtils.isEmpty(phone))
            return;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static boolean isTrue(int value) {
        return value == 1;
    }

    public static boolean isFalse(int value) {
        return value == 0;
    }

    public static boolean isTrue(String value) {
        return !TextUtils.isEmpty(value) && value.equals(Y);
    }

    public static boolean isFalse(String value) {
        return !TextUtils.isEmpty(value) && value.equals(N);
    }

    public static float isAcceptDistance(double radius, float accuracy, double placeLat, double placeLng, double userLat, double userLng)
    {
        float[] result = new float[1];
        Location.distanceBetween(placeLat, placeLng, userLat, userLng, result);
        boolean isAccept = (result[0] <= (radius + accuracy));
        String log =
                "P : " + placeLat + " / " +  placeLng
                        + "// U : " + userLat + " / " +  userLng
                        + " // dis : " + result[0]
                        + " // PR : " + radius
                        + " // UA : " + accuracy
                        + " // sum : " + (accuracy + radius)
                        + " // ist : " + isAccept;
        Tog.te("####","isAcceptDistance " + log);

        if(!isAccept)
            result[0] = -1;

        return result[0];
    }

    public static String getDistanceStr(double lat1, double lng1, double lat2, double lng2) {
        float[] result = new float[1];
        Location.distanceBetween(lat1,lng1,lat2,lng2, result);
        Tog.te("####","Distance : " + result[0]);
//        return String.format(Locale.getDefault(), "%.2fm", result[0]);
        return String.format(Locale.getDefault(), "%,dm", Math.round(result[0]));
    }


    public static void showFragmentDialog(@NonNull FragmentManager fragmentManager, @NonNull DialogFragment dialog, String tag) {
        try {
            if(fragmentManager.findFragmentByTag(tag) == null)
            {
                dialog.show(fragmentManager, tag);
            }
        } catch(RuntimeException e) { e.printStackTrace();}
    }


    public static String getPercent(int t, int v)
    {
        if(t != 0)
        {
            float percent = Math.round(1000.0f * v / t) / 10.0f;
            return String.format(Locale.getDefault(),"%.1f%%", percent);
        }
        return "0.0%";
    }
}
