package org.wa.usfmreader.api

import io.reactivex.Maybe
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

const val USFM_BASE_URL = "https://api.door43.org/"

class UsfmApi {
    var usfmClient: UsfmClient

    init {
        val httpClient = OkHttpClient.Builder()
        val builder =
                Retrofit.Builder()
                        .baseUrl(USFM_BASE_URL)
                        .addConverterFactory(
                                ScalarsConverterFactory.create()
                        ).addCallAdapterFactory(
                                RxJava2CallAdapterFactory.create()
                        )

        val retrofit =
                builder.client(
                        httpClient.build()
                ).build()

        usfmClient = retrofit.create(UsfmClient::class.java)
    }


    fun getBookUsfm(usfmUrl: String): Maybe<String> {
        return usfmClient.getBookUsfm(usfmUrl)
    }
}