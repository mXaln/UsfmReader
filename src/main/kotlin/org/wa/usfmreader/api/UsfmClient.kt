package org.wa.usfmreader.api

import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface UsfmClient {
    @GET
    fun getBookUsfm(@Url url: String): Maybe<String>
}