package org.wa.usfmreader.api

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class UsfmApi {
    var usfmClient: UsfmClient

    init {
        val httpClient = OkHttpClient.Builder()
        val builder =
                Retrofit.Builder()
                        .addConverterFactory(
                                MoshiConverterFactory.create()
                        ).addCallAdapterFactory(
                                RxJava2CallAdapterFactory.create()
                        )

        val retrofit =
                builder.client(
                        httpClient.build()
                ).build()

        usfmClient = retrofit.create(UsfmClient::class.java)
    }


    fun getBookUsfm(usfmUrl: String): Observable<String> {
        return usfmClient.getBookUsfm(usfmUrl)
    }
}