package com.snxun.kotlindemo.test.java;

/**
 * Created by liancl on 2019/8/7.
 */
public class TestLogic {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Thrad1());
        thread1.start();
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(new Thrad2());
        thread2.start();
//        try {
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private static class Thrad1 implements Runnable {
        @Override
        public void run() {

            synchronized (TestLogic.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    TestLogic.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }

        }
    }

    private static class Thrad2 implements Runnable {
        @Override
        public void run() {

            synchronized (TestLogic.class) {
                System.out.println("enter thread2...");
                System.out.println("thread2 waiting...");
                //notify  釋放鎖
                TestLogic.class.notify();

                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
            }

        }
    }

}
