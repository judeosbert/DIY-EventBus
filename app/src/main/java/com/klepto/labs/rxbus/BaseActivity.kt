package com.klepto.labs.rxbus

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    private val mBus = EventBus.getInstance()
    private val mEventListeners = BusEventListeners()

    override fun onResume() {
        super.onResume()
        mBus?.register(mEventListeners)
    }

    override fun onPause() {
        super.onPause()
        mBus?.unregister()
    }

    inner class BusEventListeners:MainThreadBusListener{
        override fun onComplete() {

        }

        override fun onNext(t: BusEvent) {
            when(t.key) {
                EventKeys.EVENT_A ->
                    Toast.makeText(applicationContext, t.data as String, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onError(e: Throwable) {

        }

    }
}
