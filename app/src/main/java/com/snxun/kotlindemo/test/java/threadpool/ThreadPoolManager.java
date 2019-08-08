package com.snxun.kotlindemo.test.java.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by liancl on 2019/8/7.
 */
public class ThreadPoolManager {
    private volatile static  ThreadPoolManager threadPoolManager;
    private ThreadPoolManager() {
    }

    public static ThreadPoolManager getInstance(){
        if (threadPoolManager == null) {
            synchronized (ThreadPoolManager.class){
                if (threadPoolManager == null) {
                    threadPoolManager = new ThreadPoolManager();
                }
            }
        }
        return threadPoolManager;
    }

    public ExecutorService getCachedThreadPool(ThreadFactory threadFactory){
        if (threadFactory==null) {
            return Executors.newCachedThreadPool();
        }
        return Executors.newCachedThreadPool(threadFactory);
    }


    public ExecutorService getFixedThreadPool(int num,ThreadFactory threadFactory){
        if (threadFactory==null) {
            return Executors.newFixedThreadPool(num);
        }
        return Executors.newFixedThreadPool(num,threadFactory);
    }

    public ExecutorService getScheduledThreadPool(int num,ThreadFactory threadFactory){
        if (threadFactory==null) {
            return Executors.newScheduledThreadPool(num);
        }
        return Executors.newScheduledThreadPool(num,threadFactory);
    }
    public ExecutorService getSingleThreadExecutor(ThreadFactory threadFactory){
        if (threadFactory==null) {
            return Executors.newSingleThreadExecutor();
        }
        return Executors.newSingleThreadExecutor(threadFactory);
    }

}
