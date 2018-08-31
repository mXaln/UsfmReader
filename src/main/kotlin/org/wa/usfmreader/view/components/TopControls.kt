package org.wa.usfmreader.view.components

import javafx.beans.property.SimpleObjectProperty
import org.wa.usfmreader.model.Book
import org.wa.usfmreader.model.Chapter
import org.wa.usfmreader.model.Language
import org.wa.usfmreader.view.BookViewModel
import org.wa.usfmreader.view.ChapterViewModel
import org.wa.usfmreader.view.LanguageViewModel
import tornadofx.*

class TopControls : View("Top") {
    val selectedLanguage = SimpleObjectProperty<Language>()
    val selectedBook = SimpleObjectProperty<Book>()
    val selectedChapter = SimpleObjectProperty<Chapter>()

    val languageViewModel = LanguageViewModel(selectedLanguage)
    val bookViewModel = BookViewModel(selectedBook)
    val chapterViewModel = ChapterViewModel(selectedChapter)

    val languages = listOf(
            Language("en", "English", listOf(
                    Book("gen", "Genesis", "", listOf(
                            Chapter(1, "Chapter 1")
                    ).observable())
            ).observable()),
            Language("ru", "Russian", listOf(
                    Book("exo", "Exodus", "", listOf(
                            Chapter(3, "Chapter 3")
                    ).observable())
            ).observable()),
            Language("es", "Spanish", listOf(
                    Book("lev", "Leviticus", "", listOf(
                            Chapter(5, "Chapter 5")
                    ).observable())
            ).observable())).observable()

    override val root = hbox {
        spacing = 20.0

        combobox(selectedLanguage, languages) {
            selectionModel.selectFirst()
        }
        combobox(selectedBook, languageViewModel.books as List<Book>) {
            selectionModel.selectFirst()
        }
        combobox(selectedChapter, bookViewModel.chapters as List<Chapter>) {
            selectionModel.selectFirst()
        }
    }
}
