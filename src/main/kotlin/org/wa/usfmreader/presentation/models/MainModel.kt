package org.wa.usfmreader.presentation.models

import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.collections.ObservableList
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.api.RemoteCatalogRepository
import org.wa.usfmreader.api.RemoteUsfmRepositoryImpl
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.usecases.BookUsecase
import org.wa.usfmreader.domain.usecases.LanguagesUsecase
import org.wa.usfmreader.persistence.LocalUsfmRepositoryImpl
import org.wa.usfmreader.persistence.UsfmFile
import org.wa.usfmreader.persistence.db.AppDatabaseImpl
import tornadofx.*


class MainModel {
    private var language: LanguageData by property()
    val languageProperty = getProperty(MainModel::language)

    private var book: BookData by property()
    val bookProperty = getProperty(MainModel::book)

    private var chapter: ChapterData by property()
    val chapterProperty = getProperty(MainModel::chapter)

    private var bookLoading: Boolean by property()
    val bookLoadingProperty = getProperty(MainModel::bookLoading)

    val languages: ObservableList<LanguageData> =
            mutableListOf<LanguageData>().observable()

    init {
        LanguagesUsecase(RemoteCatalogRepository(CatalogApi()))
                .getLanguages()
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .onErrorReturn { languages }
                .subscribe { it ->
                    languages.clear()
                    languages.addAll(it.sortedBy { it.name })
                }

    }

    fun onLanguageSelected(language: LanguageData) {
        println(language.name)
    }

    fun onBookSelected(book: BookData) {
        bookLoading = true
        BookUsecase(
                LocalUsfmRepositoryImpl(
                        UsfmFile(),
                        AppDatabaseImpl()
                ),
                RemoteUsfmRepositoryImpl(UsfmApi())
        ).getBookWithChapters(book, language)
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .onErrorReturn {
                    book
                }
                .subscribe {
                    this.book = it
                    bookLoading = false
                }
    }

    fun onNextChapterClick() {
        val nextChapter = book.chapters
                .singleOrNull { it.number == chapter.number + 1 }

        if (nextChapter != null) {
            chapter = nextChapter
        }
    }

    fun onPreviousChapterClick() {
        val prevChapter = book.chapters
                .singleOrNull { it.number == chapter.number - 1 }

        if (prevChapter != null) {
            chapter = prevChapter
        }
    }
}