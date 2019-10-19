package com.klepto.labs.rxbus

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mBus = EventBus.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            mBus?.postEvent(EventKeys.EVENT_A,"This is a sample data")
        }
    }
}
