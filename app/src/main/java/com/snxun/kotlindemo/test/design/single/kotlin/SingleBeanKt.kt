package com.snxun.kotlindemo.test.design.single.kotlin

/**
 * Created by liancl on 2019/8/2.
 *
 */
class SingleBeanKt private constructor() {

//    懒汉模式 线程不安全
//    companion object{
//        var stance: SingleBeanKt? =null
//        fun getInstancek():SingleBeanKt?{
//            if (stance==null) {
//                stance=SingleBeanKt()
//            }
//            return stance
//        }
//    }

//    //懒汉模式 线程安全
//    companion object{
//        var stance: SingleBeanKt? =null
//        @Synchronized
//        fun getInstancek():SingleBeanKt?{
//            if (stance==null) {
//                stance=SingleBeanKt()
//            }
//            return stance
//        }
//    }

//    //双重检查锁模式 这种写法弱爆了
//    companion object{
//        var stance: SingleBeanKt? =null
//
//        fun getInstancek(): SingleBeanKt? {
//            if(stance==null){
//                synchronized(SingleBeanKt.javaClass){
//                    if (stance==null) {
//                        stance=SingleBeanKt()
//                    }
//                }
//            }
//            return stance
//        }
//    }

    //双重检查锁模式
    companion object{
        val stance: SingleBeanKt by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            SingleBeanKt()
        }
    }

}

