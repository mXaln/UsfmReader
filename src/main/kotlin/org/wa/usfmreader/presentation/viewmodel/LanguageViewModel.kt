package org.wa.usfmreader.presentation.viewmodel

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.api.RemoteCatalogRepository
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.usecases.LanguagesUsecase
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

    val languages: ObservableList<LanguageData> =
        mutableListOf<LanguageData>().observable()

    private val remoteCatalogRepository = RemoteCatalogRepository(CatalogApi())
    private val languagesUc = LanguagesUsecase(remoteCatalogRepository)

    private val bookViewModel: BookViewModel by inject()

    init {
        getLanguages()
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .subscribe { it ->
                    val newLangs = it
                            .sortedBy { it.name }

                    languages.clear()
                    languages.addAll(newLangs)
                }

    }

    fun getLanguages(): Observable<List<LanguageData>> {
        return languagesUc.getLanguages()
    }

    fun onLanguageSelected(language: LanguageData?) {
        if (language != null) {
            bookViewModel.books.clear()
            bookViewModel.books.addAll(language.books)
        }
    }
}