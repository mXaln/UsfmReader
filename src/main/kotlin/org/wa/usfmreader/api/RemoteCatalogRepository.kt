package org.wa.usfmreader.api

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.CatalogRepository

class RemoteCatalogRepository(private val api: CatalogApi): CatalogRepository {

    override fun getLanguages(): Observable<List<LanguageData>> {
        return api.getLanguages()
    }
}