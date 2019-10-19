package com.klepto.labs.rxbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : BaseActivity() {
    private val mBusEventListener = Activity2BusEventListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        button.setOnClickListener {
            mBus?.postEvent(EventKeys.EVENT_A,"Second button Click")
        }
    }

    override fun onResume() {
        mBus?.register(mBusEventListener)
        super.onResume()
    }

    override fun onPause() {
        mBus?.unregister()
        super.onPause()
    }

    inner class Activity2BusEventListener:BusEventListeners(){

        override fun onComplete() {
            super.onComplete()
        }

        override fun onNext(t: BusEvent) {
            when(t.key) {
                EventKeys.EVENT_A ->
                    button.text = System.currentTimeMillis().toString()
            }
         super.onNext(t)
        }

        override fun onError(e: Throwable) {
            super.onError(e)
        }

    }
}
