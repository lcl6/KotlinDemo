package com.snxun.kotlindemo.test.java.threadpool;

import java.util.concurrent.ExecutorService;

/**
 * 测试线程池
 * FixedThreadPool、 线程数量固定 只有核心线程 没有非核心线程
 * CachedThreadPool、不固定线程池 没有核心线程  只有非核心线程
 * ScheduledThreadPool、 核心线程固定 非核心线程数不固定
 * SingleThreadExecutor  只有一个核心线程 没有非核心线程 不需要考虑同步问题，
 *
 * Created by liancl on 2019/8/7.
 */
public class TestThreadPool {

    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            ExecutorService cachedThreadPool = ThreadPoolManager.getInstance().getCachedThreadPool(null);
            int finalI = i;
            cachedThreadPool.execute(() -> {
                try {

                    System.out.println(Thread.currentThread().getName()+":"+ finalI);
                    Thread.sleep(1000);
                    //没有核心线程


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }

//        ExecutorService fixedThreadPool = ThreadPoolManager.getInstance().getFixedThreadPool(5, null);
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            fixedThreadPool.execute(() -> {
//                System.out.println(Thread.currentThread().getName()+":"+ finalI);
//
//                //只有核心线程

//            });
//
//        }
//        ExecutorService fixedThreadPool = ThreadPoolManager.getInstance().getScheduledThreadPool(5, null);
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            fixedThreadPool.execute(() -> {
//                System.out.println(Thread.currentThread().getName()+":"+ finalI);
//                // 只有核心线程 没有非核心线程

//
//            });
//
//        }
//        ExecutorService fixedThreadPool = ThreadPoolManager.getInstance().getSingleThreadExecutor(null);
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            fixedThreadPool.execute(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+":"+ finalI);
//            });
//
//        }

    }

}
