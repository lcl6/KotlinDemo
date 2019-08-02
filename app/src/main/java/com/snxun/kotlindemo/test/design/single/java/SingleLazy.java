package com.snxun.kotlindemo.test.design.single.java;

/**
 * 1.饿汉模式  空间换时间 对应
 * 不存在多线程安全等问题  但是可能造成资源浪费
 *
 * 2.静态内部内实现单例
 *
 * Created by liancl on 2019/8/2.
 */
public class SingleLazy {

    //饿汉模式
    private static final SingleLazy SINGLE_LAZY= new SingleLazy();
    private SingleLazy() {}
    public static SingleLazy getInstance(){
        return SINGLE_LAZY;
    }



    //静态内部内实现单例 推荐
    public static SingleLazy getInstanceIn(){
        return SingleIn.SINGLE_LAZY_IN;
    }
    public static class SingleIn{
        private static final  SingleLazy SINGLE_LAZY_IN= new SingleLazy();

    }




}
