package org.wa.usfmreader.presentation.viewmodel

import io.reactivex.Observable
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.usecases.LanguagesUsecase
import org.wa.usfmreader.persistence.RemoteCatalogRepository
import tornadofx.*

class LanguageViewModel(languageData: LanguageData? = null): ItemViewModel<LanguageData>(languageData) {
    val slug = bind(autocommit = true) {
        SimpleStringProperty(item?.slug ?: "")
    }
    val name = bind(autocommit = true) {
        SimpleStringProperty(item?.name ?: "")
    }
    val direction = bind(autocommit = true) {
        SimpleStringProperty(item?.direction ?: "")
    }
    val books = bind(autocommit = true) {
        SimpleListProperty<BookData>(FXCollections.observableArrayList(item?.books ?: listOf()))
    }

    private val remoteCatalogRepository = RemoteCatalogRepository(CatalogApi())
    private val languagesUc = LanguagesUsecase(remoteCatalogRepository)

    fun getLanguages(): Observable<List<LanguageData>> {
        return languagesUc.getLanguages()
    }
}