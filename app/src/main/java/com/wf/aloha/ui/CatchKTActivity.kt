package com.wf.aloha.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wf.aloha.R
import java.io.File

class CatchKTActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catch_kt)
        
//        val size = getDirSize()
    }

    private fun getDirSize( file: File) :Long{
        
        return 0
    }
}
