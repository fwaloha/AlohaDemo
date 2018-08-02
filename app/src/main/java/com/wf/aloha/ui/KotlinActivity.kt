package com.wf.aloha.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.wf.aloha.*
import com.wf.aloha.utils.LogUtils
import com.wf.aloha.utils.ToastUtils
import junit.framework.Assert.*
import java.security.acl.Group
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import kotlin.collections.ArrayList
import kotlin.reflect.KProperty

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        //函数
//        LogUtils.d("-----",funsum(1,3)+"");
        funsum(1, 3)
        funsum(i1 = 4)
        val result = haha(4)
        foo { Log.d("-----", "hahhahah") }
        foo(2) {
            Log.d("-----", "what is this?")
        }
        double(4)
        val list = changesize(1, 2, 4)
        val list2 = arrayOf(23, 44.22, 455)
        val list3 = changesize(1, 3, *list2)
        LogUtils.d("-----", list3.toString())
        1 shl 4
        val findipoint = findipoint(2.9)
        LogUtils.d("-----", "zhaodaole" + findipoint)
        varargtest(1, 2, 3342, 4346, 4, 4543)
        LogUtils.d("-----", sumlambda(8, 9).toString())
        charmode()
        checkNullFun()
        for (i in 1..4 step 2) {
            println(i)
        }
        for (i in 1 until 9) {
            println(i)
        }
        for (i in 4 downTo 1 step 2) {
            print(i)
        }
        val ome = 1_000_000
        LogUtils.d("-----", "" + ome)
        if (ome is Int) {
            LogUtils.d("-----", "ome is int")
        }
        val ab = Array(3, { i -> i * 3 })
        val text = """
            |haha
            |xxi
            |kk
            """.trimMargin()
        testdaole("kkkk")
        hasPrefix(22)
        labalcheck()
        var kotclass: KotlinTestclass = KotlinTestclass()
        fun KotlinTestclass.pt() {
            print("hshhshsh")
        }
        kotclass.lastName = "kkdkdkdk"
        LogUtils.d("-----", "${kotclass.lastName} is last name")
        kotclass.getno = 999
        LogUtils.d("-----", "no is ${kotclass.getno}")
        kotclass.getno = 1
        LogUtils.d("-----", "no is ${kotclass.getno}")
        kotclass.name = "kkdk"
        kotclass.weight = 999
        LogUtils.d("-----", "name is ${kotclass.name} weight is ${kotclass.weight}")
        kotclass.pt()
        val site = Site
        val site1 = Site
        Site.name = "kdkkd"
        LogUtils.d("-----", Site.name)
        val companion = KotlinTestclass
        bt_aloha.setText("changed button")
        bt_aloha.text = "ddd"
        val recyclerView = findViewById(R.id.recycler) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val arrayListOf = arrayListOf<String>("dkdkkd", "kkkkd", "dlsfajl")
//        toast("hahahah")
        longToast("zhe shi sha")
        val url = "http://p2p.daokoudai.com/20170710601.html"
        async {
            KTRequest(url).run()
            uiThread { toast("request performed") }
        }

        val mutableList = mutableListOf<Int>()
        mutableList.add(22)
        mutableList.add(22)
        mutableList.add(22)
        val mutableMap = mutableMapOf<String, String>()
        mutableMap.put("dd", "dds")
        val listOf = listOf<Int>(1, 2, 3, 4, 5, 6)
//        assertTrue(listOf.all { it > 0 })
//        assertFalse(listOf.any { it < 9 })
//        assertEquals(1, listOf.count { it > 8 })
        val fold = listOf.fold(4) { total, next -> total + next }
        assertEquals(25, listOf.fold(4) { total, next -> total + next })
        listOf.maxBy { -it }
//        listOf.max()
//        listOf.min()
//        listOf.reduce { acc, i -> acc + i }
//        listOf.sum()
//        listOf.drop(2)
//        listOf.dropWhile { it < 4 }
//        listOf.filterNotNull()
        val groupBy = listOf.groupBy { if (it % 2 == 0) "even" else "odd" }
        LogUtils.d("-----groupBy", groupBy.toString())
        val pair = Pair(1, 2)
        var keint: Int? = null
        keint.toString()
        val a: Int? = null
        a.toString()
        val b: Int? = 1
//        2/b
        val cost = when (b) {
            in 1..10 -> "hehe"
            in 10..100 -> "expensive"
            1 -> {
                "heheh"
            }
            else -> "kkkkkkkkkelse"
        }

        var aa: String = "kkka"
        var bb: String = "kkkb"
        var c: String = """
            |$aa
            |$bb
            """.trimMargin()
        ToastUtils.showInstance(c)

        var aaa = 1
        var bbbb = 2
        var cccc = if (aaa > bbbb) 3 else 4
        haskk("kkk")

        val kotlinClss = KotlinClss("male", 12, "usernameishahaha")
        val dataKotlin = DataKotlin("haha", 23)
        dataKotlin.copy(age = 40)

        val abc = object {
            var a = 99999999
            var b = 202303
        }
        println("${abc.a}${abc.b}")
        varaa(1, 2, 3, 4)

        //lambda表达式
        val sum = { x: Int, y: Int -> x + y }
        val i = sum(1, 2)
        val sum1: (Int, Int) -> Int = { x, y -> x + y }
        val sum2: (Int) -> Int = { x -> x + 1 }
        val sum3: (Int) -> Int = { it + 1 }
        getName("str")
        printName("Name", ::getName)


        mutableList.swap(1, 2)


        val user = User()
        user.value = 34
        println("${user.value + user.mValue}")

        val user1 = User1()
        user1.uName = "hahahhae"
        println(user1.uName)
        
        //kotlin 自带的延迟委托
        val lazyvalu:String by lazy { 
            println("hehe")
            "hhh"
        }
        lazyvalu

        var map = mapOf("name" to "Czh", "age" to 22)
        val mapOf = mapOf("age" to 12, "name" to "ehe")
        val get = mapOf.get("age")
        println("map of ggggg is $get")

        val mapOf1 = mutableMapOf<String, Any>()
        mapOf1.put("name","zhangsan")
        mapOf1.put("age",11)
        for ((k,v) in mapOf1)
            println("$k:$v")
        
        var ac = "aa"
        println(+ac)//重载操作符
    }
    
    //重载操作费
    operator fun String.unaryPlus():String{
        return this+this
    }

    //    扩展属性
    class User {
        var mValue = 0
    }

    //    扩展属性
    var User.value: Int
        get() = mValue
        set(value) {
            mValue = value
        }

    //    委托属性
    class Delegate {
        var userName = ""
//        下面的两个函数名是咋回事，默认必须这么定义这俩名吗
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            println("getvalue class name $thisRef, propertyname ${property.name}")
            return userName
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("getvalue class name $thisRef, propername is ${property.name}, result is $value")
            userName = value
        }
    }

    class User1 {
        var uName: String by Delegate()
    }

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val i1 = this[index1]
        this[index1] = this[index2]
        this[index2] = i1
    }

    private fun printName(a: String, name: (str: String) -> String): String {
        var str = "$a${name("Czh")}"
        return str
    }

    private fun getName(s: String): String {
        return s
    }

    private fun <T> varaa(vararg sss: T) {
        val arrayList = ArrayList<T>()
        for (t in sss) {
            arrayList.add(t)
        }

    }

    enum class ColorE {
        APPLE, PEAL, HAHA
    }

    private fun haskk(x: Any) {
        when (x) {
            is Int -> ""
            is String -> "kkdk"
            else -> "else"
        }
    }

    object Site {
        var url: String = "kkk"
        var name: String = "caicai"
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun labalcheck() {
        loop@ for (i in 1..100) {
            for (b in 1..3) {
                if (b == 1)
                    break@loop
            }
        }
        var ints = arrayOf(1, 2, 3)
        ints.forEach lite@{
            if (it == 0)
                return@lite
        }

        ints.forEach {
            if (it == 0)
                return@forEach
        }

        for ((index, value) in ints.withIndex()) {
            print("the $index of ints is $value")
        }
    }

    private fun hasPrefix(x: Any) {
        when (x) {
            is String -> x.startsWith("prefix")
            else -> false
        }
    }

    private fun testdaole(s: String) {
        val a = s
        LogUtils.d("------", "$a.length is ${a.length}" + "${'$'}")
    }

    fun parseInt(args: String): Int? {

        return 1
    }

    fun getobj(str: Any): Int? {
        if (str is String) {
            return str.length
        }

//        区间
        for (i in 1..4) {
            print(i)
        }
        return null
    }


    //    验证null空判断功能
    fun maind(args: Array<String>) {
        if (args.size < 2) {
            LogUtils.d("-----", "expect two integers")
        }
        val parseInt = parseInt(args[0])
        val parseInt1 = parseInt(args[1])
        if (parseInt != null && parseInt1 != null) {
            LogUtils.d("-----", "" + (parseInt * parseInt1))
        }
    }

    private fun checkNullFun() {
        var ab: String? = "22"
        val b = ab!!.toInt()
        val bb = ab?.toInt()
        val bc = ab?.toInt()
    }

    private fun charmode() {
        var a = 1
        var s1 = "a is $a"
        a = 2
        val s2 = "${s1.replace("is", "was")}, and now a is $a"
    }

    val sumlambda: (Int, Int) -> Int = { x, y -> x + y }

    fun haha(a: Int): Int {
        return 2 * a
    }

    fun funsum(i: Int = 0, i1: Int): Int {
        return i + i1
    }

    fun str(i: Int = 0, i1: Int, i2: () -> Unit) {
    }

    fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /* …… */
    }

    fun double(x: Int) = x * 2
    fun <T> changesize(vararg arry: T): List<T> {
        val result = ArrayList<T>()
        for (a in arry)
            result.add(a)
        return result
    }

    infix fun Int.haha(x: Int): Int {
        return 9999
    }

    fun dfs(graph: Group) {
        fun dfs(current: Char, visited: Set<Char>) {
        }
    }

    fun <S> ddd(hehe: S) {

    }

    val sum = { x: Int, y: Int -> x + y }
    val b = 9
    val c = "ddd"
    val dd = { x: Int, y: Int -> x * y }
    tailrec fun findipoint(x: Double = 1.0): Double = if (x == Math.cos(x)) x else findipoint(Math.cos(x))

    val o: Byte = 9
    val oz: Int = o.toInt()
    val kk: Byte = oz.toByte()
    val hl = 1L + 4
    val h = (1 shl 2) and 0x000ff000
    fun tsum(a: Int, b: Int) = a + b
    fun dunit(a: Int, b: Int) {
        LogUtils.d("-----", "dklslj")
    }

    public fun duit(a: Int, b: String) {
        LogUtils.d("-----", "dklslj")
    }

    fun varargtest(vararg a: Int) {
        for (vt in a) {
            LogUtils.d("-----", vt.toString())
        }
    }
}
