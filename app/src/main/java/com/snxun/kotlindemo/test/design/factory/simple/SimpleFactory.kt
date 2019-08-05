package com.snxun.kotlindemo.test.design.factory.simple

/**
 * 简单工厂模式
 * 提供一个创建对象实例的功能 无需关心其具体实现
 *
 * 结合单例
 * Created by liancl on 2019/8/5.
 *
 */
class SimpleFactory private constructor(){

    //内部类内构建实例
    public fun  getInstance():SimpleFactory{
        return  SimpleFactoryin.instance
    }
    object SimpleFactoryin{
        val instance=SimpleFactory()
    }

    //双重校验锁
    companion object{
        val simpleFactory : SimpleFactory by lazy (mode=LazyThreadSafetyMode.SYNCHRONIZED){
            SimpleFactory()
        }
    }

  public  fun CreatCar(name:String) : String{
      return when {
          "bmw".equals(name) -> {
              "宝马"
          }
          "benz".equals(name) -> {
              "奔驰"
          }
          else ->{
              "没有这种车"
          }
      }
    }


}