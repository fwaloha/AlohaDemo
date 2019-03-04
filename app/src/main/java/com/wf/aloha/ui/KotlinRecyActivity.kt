package com.wf.aloha.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearSnapHelper
import com.wf.aloha.R
import kotlinx.android.synthetic.main.activity_kotlin_recy.*

class KotlinRecyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_recy)

        val oneRecy = one_rec_view
        val moreRecy = more_rec_view

        val linearSnapHelper = LinearSnapHelper()
        linearSnapHelper.attachToRecyclerView(moreRecy)
        
    }
}
