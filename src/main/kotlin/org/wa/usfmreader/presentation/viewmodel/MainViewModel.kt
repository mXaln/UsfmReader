package org.wa.usfmreader.presentation.viewmodel

import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.models.MainModel
import tornadofx.*

class MainViewModel: ViewModel() {

    private val model = MainModel()

    var languageProperty = bind { model.languageProperty }
    var bookProperty = bind { model.bookProperty }
    var chapterProperty = bind { model.chapterProperty }

    var chaptersUpdatedProperty = bind { model.chaptersUpdatedProperty }

    val languages = model.languages
    val books = model.books
    val chapters = model.chapters

    fun handleLanguageSelected(language: LanguageData?) {
        model.onLanguageSelected(language)
    }

    fun handleBookSelected(book: BookData?) {
        model.onBookSelected(book)
    }
}