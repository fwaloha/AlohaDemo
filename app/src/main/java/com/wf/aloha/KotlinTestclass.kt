package com.wf.aloha

/**
 * Created by wangfei on 2018/1/3.
 * 添加open代表类可以被继承，方法一样。如果复写方法需要在方法前加override
 */
open class KotlinTestclass {

    init {
        print("this is init")
    }

    companion object {
        fun creatsth(){
            print("hshshhsh")
        }
    }
    var lastName: String = "wak"
        get() = field.toUpperCase()
        set
    var getno: Int = 100
        get() = field
        set(value) {
            if (value > 10) {
                field = -1
            } else {
                field = value
            }
        }
    var height: Float = 100.2f
        private set

    var name: String = ""
    var weight: Int = 0
    lateinit var cgn: String

    inner class hha {
        fun hhh() {
            var o = this@KotlinTestclass
            o.name = "kkk"
        }

    }
    
    fun KotlinTestclass.pt(){
        print("hshhshsh")
    }
}