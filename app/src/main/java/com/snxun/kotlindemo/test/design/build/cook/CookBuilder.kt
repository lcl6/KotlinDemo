package com.snxun.kotlindemo.test.design.build.cook

/**
 * Created by liancl on 2019/7/31.
 *
 */
class CookBuilder{

    private lateinit var cookCabage:String
    private lateinit var cookMeat:String
    private lateinit var cookEgg:String
    private lateinit var cookTomato:String
    private lateinit var cookApple:String

    private var cookBean: CookBean=CookBean()

    public fun setCabage(cookCabage :String):CookBuilder{
        this.cookCabage=cookCabage
        return this
    }

    public fun setMeat(meat :String):CookBuilder{
        this.cookMeat=meat
        return this
    }
    public fun setEgg(egg :String):CookBuilder{
        this.cookEgg=egg
        return this
    }
    public fun setTomato(tomoto :String):CookBuilder{
        this.cookTomato=tomoto
        return this
    }
    public fun setApple(apple :String):CookBuilder{
        this.cookApple=apple
        return this
    }


    public fun build() :CookBean{
        cookBean.cookCabage=cookCabage
        cookBean.cookApple=cookApple
        cookBean.cookEgg=cookEgg
        cookBean.cookMeat=cookMeat
        cookBean.cookTomato=cookTomato
        return cookBean
    }



}