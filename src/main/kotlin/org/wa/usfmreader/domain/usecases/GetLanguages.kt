package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.persistence.CatalogRepository

open class GetLanguages(private val repository: CatalogRepository): BaseUsecase<List<LanguageData>> {
    override fun createObservable(data: Any?): Observable<List<LanguageData>> {
        return repository.getLanguages()
    }
}