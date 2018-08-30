package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer
import org.wa.usfmreader.domain.entities.ChapterEntity

open class GetChapters(transformer: Transformer<List<ChapterEntity>>,
                       private val repository: CatalogRepository) : UseCase<List<ChapterEntity>>(transformer) {

    fun getChapters(slug: String): Observable<List<ChapterEntity>> {
        val data = HashMap<String, String>()
        data["slug"] = slug
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<ChapterEntity>> {
        val slug = data?.get("slug")
        slug?.let {
            return repository.getChapters(it as String)
        } ?: return Observable.error { IllegalArgumentException("Book slug must be provided.") }
    }

}