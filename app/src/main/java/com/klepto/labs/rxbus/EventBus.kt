package com.klepto.labs.rxbus

import android.view.KeyEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class EventBus private constructor() {
    private var mDisposable:Disposable?= null
    fun register(listener: MainThreadBusListener) {
        mDisposable = mEventSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    listener.onNext(it)
                },
                onError = {
                    listener.onError(it)
                },
                onComplete = {
                    listener.onComplete()
                }
            )
    }

    fun unregister() = mDisposable?.dispose()

    fun postEvent(key:EventKeys,data:Any){
        mEventSubject.onNext(BusEvent(key,data))

    }

    private val mEventSubject = PublishSubject.create<BusEvent>();

    companion object{
        private var mInstance:EventBus? = null
        fun getInstance():EventBus?{
            if(mInstance == null){
                mInstance = EventBus()
            }
            return mInstance
        }
    }
}

data class BusEvent(val key:EventKeys,val data:Any)

enum class EventKeys{
    EVENT_A
}
interface MainThreadBusListener{
    fun onComplete()

    fun onNext(t: BusEvent)

    fun onError(e: Throwable)
}

