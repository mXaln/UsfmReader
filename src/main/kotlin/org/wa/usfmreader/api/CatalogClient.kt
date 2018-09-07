package org.wa.usfmreader.api

import io.reactivex.Observable
import org.wa.usfmreader.api.models.LanguagesListResult
import retrofit2.http.GET

interface CatalogClient {

    @GET("v3/catalog.json")
    fun getLanguages(): Observable<LanguagesListResult>
}