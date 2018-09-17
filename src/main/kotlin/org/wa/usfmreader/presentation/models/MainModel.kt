package org.wa.usfmreader.presentation.models

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
    private var language: LanguageData by property()
    val languageProperty = getProperty(MainModel::language)

    private var book: BookData by property()
    val bookProperty = getProperty(MainModel::book)

    private var chapter: ChapterData by property()
    val chapterProperty = getProperty(MainModel::chapter)

    val languages: ObservableList<LanguageData> =
            mutableListOf<LanguageData>().observable()

    private val bookUc = BookUsecase(RemoteUsfmRepository(UsfmApi()))
    private val languagesUc = LanguagesUsecase(RemoteCatalogRepository(CatalogApi()))

    init {
        languagesUc.getLanguages()
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .subscribe { it ->
                    languages.clear()
                    languages.addAll(it.sortedBy { it.name })
                }
    }

    fun onLanguageSelected(language: LanguageData) {
    }

    fun onBookSelected(book: BookData) {
        bookUc.getBookWithChapters(book)
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                    this.book = it
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