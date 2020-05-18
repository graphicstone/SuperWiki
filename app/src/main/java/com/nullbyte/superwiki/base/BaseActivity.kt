package com.nullbyte.superwiki.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        onActivityStart()
    }

    override fun onResume() {
        super.onResume()
        onActivityResume()
    }

    override fun onStop() {
        super.onStop()
        onActivityStop()
    }

    override fun onPause() {
        super.onPause()
        onActivityPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        onActivityDestroy()
    }

    abstract fun onActivityStart()
    abstract fun onActivityResume()
    abstract fun onActivityStop()
    abstract fun onActivityPause()
    abstract fun onActivityDestroy()
}