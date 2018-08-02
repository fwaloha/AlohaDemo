package com.wf.aloha

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by wangfei on 2018/1/3.
 */
class KotInt<T>() : ReadWriteProperty<Any?, T> {
    private var valuse: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        throw UnsupportedOperationException()
        return valuse ?: throw IllegalStateException(".name}")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.valuse = if (this.valuse == null) {
            value
        }else throw IllegalStateException("ddkdd")
    }
    
    fun <A, B> A.to(that:B):Pair<A,B> = Pair(this,that)
}