package com.snxun.kotlindemo.test.design.single.java;

/**
 * 单列模式 懒汉模式  时间换空间
 *  1 加 synchronized 线程安全 会消耗不必要资源
 *  2 不加 synchronized 线程不安全
 *  3 双重检查 第一个判空是为了不必要的同步 第二个判空是为了重复生成对象
 *       jdk1.5之前存在无序性 和线程共享变量不可见 可用volatile
 *
 *
 * Created by liancl on 2019/8/2.
 */
public class SingleBean {



    private  SingleBean() {}
    //懒汉模式 synchronized  线程安全
    private static SingleBean singleBean =null;
    public static synchronized SingleBean getInstance(){
        if (singleBean == null) {
            singleBean = new SingleBean();
        }
        return singleBean;
    }

    //双重检查 推荐
    private volatile static SingleBean singleBeanChenck =null;
    public static SingleBean getInstanceDoubleCheck(){
        //第一个判空是为了 防止多次进入锁
        if (singleBeanChenck == null) {
            synchronized (SingleBean.class){
                //第二次判空是为了 防止多次声生成
                if(singleBeanChenck==null){
                    singleBeanChenck= new SingleBean();
                }
            }
        }
        return  singleBeanChenck;
    }





}
