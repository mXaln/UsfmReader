package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.persistence.CatalogRepository

open class GetChapter(private val repository: CatalogRepository): BaseUsecase<ChapterData> {
    override fun createObservable(data: Any?): Observable<ChapterData> {
        return repository.getChapter(data as Int)
    }
}