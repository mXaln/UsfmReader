package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.persistence.CatalogRepository

open class GetLanguage(private val repository: CatalogRepository): BaseUsecase<LanguageData> {
    override fun createObservable(data: Any?): Observable<LanguageData> {
        return repository.getLanguage(data as String)
    }
}