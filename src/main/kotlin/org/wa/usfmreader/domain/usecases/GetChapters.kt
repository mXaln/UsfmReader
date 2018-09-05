package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.persistence.CatalogRepository

open class GetChapters(private val repository: CatalogRepository): BaseUsecase<List<ChapterData>> {
    override fun createObservable(data: Any?): Observable<List<ChapterData>> {
        return repository.getChapters(data as String)
    }
}