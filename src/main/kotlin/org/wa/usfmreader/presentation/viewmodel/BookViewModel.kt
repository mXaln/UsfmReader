package org.wa.usfmreader.presentation.viewmodel

import io.reactivex.Observable
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.domain.usecases.BookUsecase
import org.wa.usfmreader.persistence.RemoteUsfmRepository
import tornadofx.*

class BookViewModel(bookData: BookData? = null): ItemViewModel<BookData>(bookData) {
    val slug = bind(autocommit = true) {
        SimpleStringProperty(item?.slug ?: "")
    }
    val name = bind(autocommit = true) {
        SimpleStringProperty(item?.name ?: "")
    }
    val sort = bind(autocommit = true) {
        SimpleIntegerProperty(item?.sort ?: 0)
    }
    val usfmUrl = bind(autocommit = true) {
        SimpleStringProperty(item?.usfmUrl ?: "")
    }
    val chapters = bind(autocommit = true) {
        SimpleListProperty<ChapterData>(FXCollections.observableArrayList(item?.chapters ?: listOf()))
    }

    private val remoteUsfmRepository = RemoteUsfmRepository(UsfmApi())
    private val bookUc = BookUsecase(remoteUsfmRepository)

    fun getBook(book: BookData): Observable<BookData> {
        return bookUc.getBook(book)
    }
}