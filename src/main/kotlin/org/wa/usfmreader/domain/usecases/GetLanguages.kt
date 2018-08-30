package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Transformer
import org.wa.usfmreader.domain.entities.LanguageEntity

open class GetLanguages(transformer: Transformer<List<LanguageEntity>>,
                            private val repository: CatalogRepository) : UseCase<List<LanguageEntity>>(transformer) {
    override fun createObservable(data: Map<String, Any>?): Observable<List<LanguageEntity>> {
        return repository.getLanguages()
    }

}