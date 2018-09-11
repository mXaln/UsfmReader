package org.wa.usfmreader.presentation.viewmodel

import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.models.MainModel
import tornadofx.*

class MainViewModel: ViewModel() {

    private val model = MainModel()

    var chapterNumberProperty = bind { model.chapterNumberProperty }
    var chapterTextProperty = bind { model.chapterTextProperty }
    var languageProperty = bind { model.languageProperty }
    var bookProperty = bind { model.bookProperty }
    var bookNameProperty = bind { model.bookNameProperty }
    var chapterProperty = bind { model.chapterProperty }

    val languages = model.languages
    val books = model.books
    val chapters = model.chapters

    fun handleLanguageSelected(language: LanguageData?) {
        model.onLanguageSelected(language)
    }

    fun handleBookSelected(book: BookData?) {
        model.onBookSelected(book)
    }

    fun handleChapterSelected(chapter: ChapterData?) {
        println(chapter)
    }
}