package org.wa.usfmreader.presentation.viewmodel

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.wa.usfmreader.api.RemoteUsfmRepository
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.domain.usecases.BookUsecase
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

    val books: ObservableList<BookData> =
            mutableListOf<BookData>().observable()

    private val languageViewModel: LanguageViewModel by inject()
    val languageEmpty = languageViewModel.empty
    private val chapterViewModel: ChapterViewModel by inject()

    private val remoteUsfmRepository = RemoteUsfmRepository(UsfmApi())
    private val bookUc = BookUsecase(remoteUsfmRepository)

    fun getBook(book: BookData): Observable<BookData> {
        return bookUc.getBookWithChapters(book)
    }

    fun onBookSelected(book: BookData?) {
        if (book != null) {
            chapterViewModel.text.value = ""
            getBook(book)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.computation())
                    .subscribe {
                        item = it
                        chapterViewModel.chapters.clear()
                        chapterViewModel.chapters.addAll(it.chapters)
                        chapterViewModel.selectFirst()
                    }
        }
        else {
            chapterViewModel.clear()
        }
    }
}