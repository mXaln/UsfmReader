package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer
import org.wa.usfmreader.domain.entities.ChapterEntity

open class GetChapter(transformer: Transformer<ChapterEntity>,
                      private val repository: CatalogRepository) : UseCase<ChapterEntity>(transformer) {

    fun getChapter(number: Int): Observable<ChapterEntity> {
        val data = HashMap<String, Int>()
        data["number"] = number
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<ChapterEntity> {
        val number = data?.get("number")
        number?.let {
            return repository.getChapter(it as Int)
        } ?: return Observable.error { IllegalArgumentException("Chapter number must be provided.") }
    }

}