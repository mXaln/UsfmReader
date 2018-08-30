package org.wa.usfmreader.data

import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("v3/catalog.json")
    fun getLanguages(): Observable<LanguagesListResult>

    @GET("")
    fun getBookUsfm(): Observable<String>
}