package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer
import org.wa.usfmreader.domain.entities.LanguageEntity

open class GetLanguage(transformer: Transformer<LanguageEntity>,
                       private val repository: CatalogRepository) : UseCase<LanguageEntity>(transformer) {

    fun getLanguage(langSlug: String): Observable<LanguageEntity> {
        val data = HashMap<String, String>()
        data["slug"] = langSlug
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<LanguageEntity> {
        val langSlug = data?.get("slug")
        langSlug?.let {
            return repository.getLanguage(it as String)
        } ?: return Observable.error { IllegalArgumentException("Language slug must be provided.") }
    }

}