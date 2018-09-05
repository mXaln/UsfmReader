package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.persistence.CatalogRepository

open class GetBook(private val repository: CatalogRepository): BaseUsecase<BookData> {
    override fun createObservable(data: Any?): Observable<BookData> {
        return repository.getBook(data as String)
    }
}