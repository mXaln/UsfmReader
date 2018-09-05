package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.persistence.CatalogRepository

class GetBookUsfm(private val repository: CatalogRepository): BaseUsecase<String> {
    override fun createObservable(data: Any?): Observable<String> {
        return repository.getBookUsfm(data as String)
    }
}