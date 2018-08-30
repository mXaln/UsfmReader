package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer

class GetBookUsfm(transformer: Transformer<String>,
                  private val repository: CatalogRepository): UseCase<String>(transformer) {

    fun getBookUsfm(bookSlug: String): Observable<String> {
        val data = HashMap<String, String>()
        data["slug"] = bookSlug
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<String> {
        val bookSlug = data?.get("slug")
        bookSlug?.let {
            return repository.getBookUsfm(it as String)
        } ?: return Observable.error { IllegalArgumentException("Book slug must be provided.") }
    }

}