package org.wa.usfmreader.presentation.models

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.collections.ObservableList
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.api.RemoteCatalogRepository
import org.wa.usfmreader.api.RemoteUsfmRepository
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.usecases.BookUsecase
import org.wa.usfmreader.domain.usecases.LanguagesUsecase
import tornadofx.*

class MainModel {
    var chapterNumber: Int by property(1)
    val chapterNumberProperty = getProperty(MainModel::chapterNumber)

    var chapterText: String by property("")
    val chapterTextProperty = getProperty(MainModel::chapterText)

    var language: LanguageData by property()
    val languageProperty = getProperty(MainModel::language)

    var book: BookData by property()
    val bookProperty = getProperty(MainModel::book)

    var bookName: String by property()
    val bookNameProperty = getProperty(MainModel::bookName)

    var chapter: ChapterData by property()
    val chapterProperty = getProperty(MainModel::chapter)

    val languages: ObservableList<LanguageData> =
            mutableListOf<LanguageData>().observable()
    val books: ObservableList<BookData> =
            mutableListOf<BookData>().observable()
    val chapters: ObservableList<ChapterData> =
            mutableListOf<ChapterData>().observable()

    private val remoteCatalogRepository = RemoteCatalogRepository(CatalogApi())
    private val languagesUc = LanguagesUsecase(remoteCatalogRepository)

    private val remoteUsfmRepository = RemoteUsfmRepository(UsfmApi())
    private val bookUc = BookUsecase(remoteUsfmRepository)

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

    private fun getLanguages(): Observable<List<LanguageData>> {
        return languagesUc.getLanguages()
    }

    fun getBook(book: BookData): Observable<BookData> {
        return bookUc.getBookWithChapters(book)
    }

    fun onLanguageSelected(language: LanguageData?) {
        if (language != null) {
            books.clear()
            books.addAll(language.books)
        }
    }

    fun onBookSelected(book: BookData?) {
        if (book != null) {
            bookName = book.name
            chapterText = ""

            getBook(book)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.computation())
                    .subscribe {
                        chapters.clear()
                        chapters.addAll(it.chapters)
                        //chapterViewModel.selectFirst()
                    }
        }
        else {
            //chapterViewModel.clear()
        }
    }
}