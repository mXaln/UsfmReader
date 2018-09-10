package org.wa.usfmreader.domain

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData

interface CatalogRepository {
    fun getLanguages(): Observable<List<LanguageData>>
}