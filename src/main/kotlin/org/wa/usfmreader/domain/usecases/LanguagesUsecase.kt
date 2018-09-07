package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.persistence.CatalogRepository

class LanguagesUsecase(private val repository: CatalogRepository) {
    fun getLanguages(): Observable<List<LanguageData>> {
        return repository.getLanguages()
    }
}