package com.hee1kang.klib.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ViewCache {

    private Context mContext;
    private final int[] mResList;
    private SparseArray<Cache> mCacheList;
    private ViewGroup viewGroup = null;

    public ViewCache(@NonNull Context context, @NonNull int[] resList) {
        this.mContext = context;
        this.mResList = resList;
        this.mCacheList = new SparseArray<>();
    }

    private Cache getCache(int id)
    {
        Cache cache = mCacheList.get(id);
        if(cache == null)
        {
            cache = new Cache();
            mCacheList.put(id, cache);
        }

        return cache;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @WorkerThread
    public void init(int... size)
    {
        init(null , size);
    }

    @WorkerThread
    public void init(ViewGroup viewgroup , int... size)
    {
        this.viewGroup = viewgroup;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        for(int i = 0; i < size.length; i++)
        {
            if(size[i] > -1 && i < mResList.length)
            {
                int id = mResList[i];
                Cache cache = getCache(id);
                cache.max = size[i];
                for(int j = 0; j < size[i]; j++)
                {
                    View view = layoutInflater.inflate(id, viewGroup, false);
                    cache.add(new WeakReference<View>(view));
                }
            }
        }
    }

    private View inflateView(int id)
    {
        return LayoutInflater.from(mContext).inflate(id, viewGroup, false);
    }

    public View getView(int id)
    {
        Cache cache = getCache(id);
        WeakReference<View> wr = cache.poll();
        if(wr != null && wr.get() != null)
        {
            return wr.get();
        }

        return inflateView(id);
    }

    public void ClearView(@NonNull ViewGroup viewGroup , int id)
    {
        Cache cache = getCache(id);
        for(int i = 0; i < viewGroup.getChildCount(); i++)
        {
            if(cache.isFull())
                break;
            View view = viewGroup.getChildAt(i);
            if(view != null) {
                view.setTag(null);
                WeakReference<View> WR = new WeakReference<>(view);
                cache.add(WR);
            }
        }
        viewGroup.removeAllViews();
    }

    class Cache
    {
        final private ConcurrentLinkedQueue<WeakReference<View>> cache;
        private int max = 20;

        public Cache() {
            this.cache = new ConcurrentLinkedQueue<WeakReference<View>>();
        }

        public void add(@NonNull WeakReference<View> wr) {
            cache.add(wr);
        }

        public WeakReference<View> poll() {
            return cache.poll();
        }

        public boolean isFull()
        {
            return cache.size() >= max;
        }

        public void clear() {
            cache.clear();
        }
    }

    public void onDestroy()
    {
        if(mCacheList != null)
        {
            for(int i = 0; i < mCacheList.size(); i++)
            {
                try {
                    Cache cache = mCacheList.valueAt(i);
                    if(cache != null)
                        cache.clear();
                } catch(RuntimeException e) {}
            }
            mCacheList.clear();
        }
    }

}
