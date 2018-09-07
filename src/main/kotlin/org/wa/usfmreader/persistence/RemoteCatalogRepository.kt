package org.wa.usfmreader.persistence

import io.reactivex.Observable
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.data.entities.LanguageData

class RemoteCatalogRepository(private val api: CatalogApi): CatalogRepository {

    override fun getLanguages(): Observable<List<LanguageData>> {
        val mapper = LanguagesMapper()
        return api.getLanguages().map { results ->
            results.languages.map { mapper.mapFrom(it) }
        }
    }
}