package com.wf.aloha

import android.database.sqlite.SQLiteOpenHelper
import com.wf.aloha.utils.LogUtils
import java.net.URL
import java.sql.DatabaseMetaData

/**
 * Created by wangfei on 2018/1/12.
 */
class KTRequest (val url:String){
     fun run(){
        val readText = URL(url).readText()
         val mapOf = mapOf<String, Any?>("ddd" to "ddd", "int" to 222)
        LogUtils.d("-----kk",javaClass.simpleName+readText)
    }
    
    class Configuration(map:Map<String,Any?>){
        val width:Int by map
        val height:Int by map
        val dp:Int by map
        val deviceName:String by map
    }
    
    val config = Configuration(mapOf(
            "width" to 1010,
            "height" to 222,
            "dp" to 11,
            "deviceName" to "kkkkk"
    ))

}