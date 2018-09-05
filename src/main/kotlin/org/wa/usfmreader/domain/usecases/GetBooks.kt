package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.persistence.CatalogRepository

open class GetBooks(private val repository: CatalogRepository): BaseUsecase<List<BookData>> {
    override fun createObservable(data: Any?): Observable<List<BookData>> {
        return repository.getBooks(data as String)
    }
}