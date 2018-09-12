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
                    languages.clear()
                    languages.addAll(it.sortedBy { it.name })
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

    fun onLanguageSelected(language: LanguageData) {
        chapters.clear()
        books.clear()
        books.setAll(language.books)
    }

    fun onBookSelected(book: BookData) {
        chapters.clear()
        getBook(book)
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                    chapters.setAll(it.chapters)
                }
    }

    fun onNextChapterClick() {
        val nextChapter = chapters
                .singleOrNull { it.number == chapter.number + 1 }

        if (nextChapter != null) {
            chapter = nextChapter
        }
    }

    fun onPreviousChapterClick() {
        val prevChapter = chapters
                .singleOrNull { it.number == chapter.number - 1 }

        if (prevChapter != null) {
            chapter = prevChapter
        }
    }
}