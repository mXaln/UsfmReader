package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer
import org.wa.usfmreader.domain.entities.BookEntity

open class GetBook(transformer: Transformer<BookEntity>,
                   private val repository: CatalogRepository) : UseCase<BookEntity>(transformer) {

    fun getBook(slug: String): Observable<BookEntity> {
        val data = HashMap<String, String>()
        data["slug"] = slug
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<BookEntity> {
        val slug = data?.get("slug")
        slug?.let {
            return repository.getBook(it as String)
        } ?: return Observable.error { IllegalArgumentException("Book slug must be provided.") }
    }

}