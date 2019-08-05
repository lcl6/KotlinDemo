package com.snxun.kotlindemo.test.design.factory.abstractf

import com.snxun.kotlindemo.test.design.factory.methor.BenzFactory
import com.snxun.kotlindemo.test.design.factory.methor.BmwFactory
import com.snxun.kotlindemo.test.design.factory.simple.CarName

/**
 * Created by liancl on 2019/8/5.
 *
 */
 class MyAbstractFactory private constructor(){
  companion object{
   val instance:MyAbstractFactory by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){
      MyAbstractFactory()
     }
  }

   fun getCar(name:String): CarName {
    return    when{
      name.equals("bmw") ->{
       val bmwFactory = BmwFactory()
       bmwFactory.getCar()
      }
     name.equals("benz") ->{
      val benzFactory = BenzFactory()
      benzFactory.getCar()
     }
     else -> {
      val bmwFactory = BmwFactory()
      bmwFactory.getCar()
     }
    }


   }



}