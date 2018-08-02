package com.wf.aloha.bean

/**
 * Created by wangfei on 2018/1/12.
 */
class KTBean {
    var name:String=""
    get() = field.toUpperCase()
    set(value){
        field = "name: $value"
    }
    
    companion object {
        val app_id = "dkddkd"
    }
}