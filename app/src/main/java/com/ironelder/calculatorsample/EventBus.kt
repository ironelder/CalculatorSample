package com.ironelder.calculatorsample

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject




object EventBus {
    private val eventBus:PublishSubject<kotlin.Any?>? = PublishSubject.create<kotlin.Any?>()
    private val resultBus:PublishSubject<kotlin.Any?>? = PublishSubject.create<kotlin.Any?>()

    fun send(o: Any?) {
        eventBus!!.onNext(o!!)
    }

    fun toObservable(): Observable<Any?>? {
        return eventBus
    }

    fun result(o: Any?) {
        resultBus!!.onNext(o!!)
    }

    fun toObservableResult(): Observable<Any?>? {
        return resultBus
    }
}