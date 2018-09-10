package org.wa.usfmreader.api

import io.reactivex.Observable
import okhttp3.OkHttpClient
import org.wa.usfmreader.api.mappers.LanguagesMapper
import org.wa.usfmreader.data.entities.LanguageData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val CATALOG_BASE_URL = "https://api.door43.org/"

class CatalogApi {
    var catalogClient: CatalogClient

    init {
        val httpClient = OkHttpClient.Builder()
        val builder =
                Retrofit.Builder()
                        .baseUrl(CATALOG_BASE_URL)
                        .addConverterFactory(
                                MoshiConverterFactory.create()
                        ).addCallAdapterFactory(
                                RxJava2CallAdapterFactory.create()
                        )

        val retrofit =
                builder.client(
                    httpClient.build()
                ).build()

        catalogClient = retrofit.create(CatalogClient::class.java)
    }


    fun getLanguages(): Observable<List<LanguageData>> {
        val mapper = LanguagesMapper()
        return catalogClient.getLanguages().map { results ->
            results.languages
                    .filter { it.resources
                            .map { it.identifier }
                            .any { listOf("ulb","avd").contains(it) } } // filter languages that don't have these projects
                    .map { mapper.mapFrom(it) }
        }
    }
}