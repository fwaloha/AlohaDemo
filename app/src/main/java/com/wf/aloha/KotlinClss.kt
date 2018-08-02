package com.wf.aloha

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by wangfei on 2018/3/19.
 */
class KotlinClss(userName: String) {
    

    init {
        println(userName + ":init")
    }

    constructor() : this("dkdk") {
        println("wu can")

    }

    constructor(age: Int) : this("skdk") {
        println("$age")
    }

    constructor(name: String, age: Int) : this(age) {

    }

    constructor(sex: String, age: Int, userName: String) : this() {
        println(sex + ":" + age + ":" + "$userName")
    }

    private fun getName() = object {
        val name = "haha"
    }
    
    fun getAge() = object {
        val age = 22
    }
    
    fun get(){
        getName().name
//        getAge().age   //error
    }
}