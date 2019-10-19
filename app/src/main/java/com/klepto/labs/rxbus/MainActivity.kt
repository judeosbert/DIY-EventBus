package com.klepto.labs.rxbus

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            mBus?.postEvent(EventKeys.EVENT_A,"This is a sample data")
        }
    }
}
