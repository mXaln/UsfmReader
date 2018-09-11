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
    var language: LanguageData by property()
    val languageProperty = getProperty(MainModel::language)

    var book: BookData by property()
    val bookProperty = getProperty(MainModel::book)

    var chapter: ChapterData by property()
    val chapterProperty = getProperty(MainModel::chapter)

    var chaptersUpdated: Boolean by property()
    val chaptersUpdatedProperty = getProperty(MainModel::chaptersUpdated)

    val languages: ObservableList<LanguageData> =
            mutableListOf<LanguageData>().observable()
    val books: ObservableList<BookData> =
            mutableListOf<BookData>().observable()
    val chapters: ObservableList<ChapterData> =
            mutableListOf<ChapterData>().observable()

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
        val remoteCatalogRepository = RemoteCatalogRepository(CatalogApi())
        val languagesUc = LanguagesUsecase(remoteCatalogRepository)

        return languagesUc.getLanguages()
    }

    private fun getBook(book: BookData): Observable<BookData> {
        val remoteUsfmRepository = RemoteUsfmRepository(UsfmApi())
        val bookUc = BookUsecase(remoteUsfmRepository)

        return bookUc.getBookWithChapters(book)
    }

    fun onLanguageSelected(language: LanguageData?) {
        if (language != null) {
            books.clear()
            books.addAll(language.books)
        }
    }

    fun onBookSelected(book: BookData?) {
        chaptersUpdated = false

        if (book != null) {
            getBook(book)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.computation())
                    .subscribe {
                        chapters.clear()
                        chapters.addAll(it.chapters)

                        chaptersUpdated = true
                    }
        }
        else {
            chaptersUpdated = false
        }
    }
}