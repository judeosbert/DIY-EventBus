package com.klepto.labs.rxbus

import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    protected val mBus = EventBus.getInstance()
    private val mEventListeners = BusEventListeners()

    override fun onResume() {
        super.onResume()
        mBus?.register(mEventListeners)
    }

    override fun onPause() {
        super.onPause()
        mBus?.unregister()
    }

    open inner class BusEventListeners:MainThreadBusListener{
        @CallSuper
        override fun onComplete() {

        }

        @CallSuper
        override fun onNext(t: BusEvent) {
            when(t.key) {
                EventKeys.EVENT_A ->
                    Toast.makeText(applicationContext, "From BaseActivity"+t.data as String, Toast.LENGTH_SHORT).show()
            }
        }

        @CallSuper
        override fun onError(e: Throwable) {

        }

    }
}
