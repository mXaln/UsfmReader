package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable

interface BaseUsecase<T> {
    abstract fun createObservable(data: Any? = null): Observable<T>

    fun observable(withData: Any? = null): Observable<T> {
        return createObservable(withData)
    }
}