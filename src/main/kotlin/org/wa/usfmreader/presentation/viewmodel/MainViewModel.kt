package org.wa.usfmreader.presentation.viewmodel

import javafx.beans.value.ObservableObjectValue
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.models.MainModel
import tornadofx.*

class MainViewModel: ViewModel() {

    private val model = MainModel()

    var languageProperty: ObservableObjectValue<LanguageData> =
            bind(autocommit = true) { model.languageProperty }
    var bookProperty: ObservableObjectValue<BookData> =
            bind(autocommit = true) { model.bookProperty }
    var chapterProperty: ObservableObjectValue<ChapterData> =
            bind(autocommit = true) { model.chapterProperty }

    var bookLoadingProperty = bind { model.bookLoadingProperty }

    val languages = model.languages

    fun handleLanguageSelected(language: LanguageData) {
        model.onLanguageSelected(language)
    }

    fun handleBookSelected(book: BookData) {
        model.onBookSelected(book)
    }

    fun handleNextChapterClick() {
        model.onNextChapterClick()
    }

    fun handlePreviousChapterClick() {
        model.onPreviousChapterClick()
    }
}