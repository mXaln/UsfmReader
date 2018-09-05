package org.wa.usfmreader.presentation.viewmodel

import io.reactivex.Observable
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.persistence.RemoteCatalogRepository
import org.wa.usfmreader.presentation.model.Book
import org.wa.usfmreader.presentation.model.Language
import tornadofx.*

class LanguageViewModel(property: ObjectProperty<Language>): ItemViewModel<Language>(itemProperty = property) {
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
        SimpleListProperty<Book>(FXCollections.observableArrayList(item?.books ?: listOf()))
    }

    private val remoteCatalogRepository = RemoteCatalogRepository(CatalogApi())

    fun getLanguages(): Observable<List<LanguageData>> {
        return remoteCatalogRepository.getLanguages()
    }
}