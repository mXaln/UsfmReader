package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer
import org.wa.usfmreader.domain.entities.BookEntity

open class GetBooks(transformer: Transformer<List<BookEntity>>,
                    private val repository: CatalogRepository) : UseCase<List<BookEntity>>(transformer) {

    fun getBooks(slug: String): Observable<List<BookEntity>> {
        val data = HashMap<String, String>()
        data["slug"] = slug
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<BookEntity>> {
        val slug = data?.get("slug")
        slug?.let {
            return repository.getBooks(it as String)
        } ?: return Observable.error { IllegalArgumentException("Language slug must be provided.") }
    }

}