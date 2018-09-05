package org.wa.usfmreader.presentation.components

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.model.Book
import org.wa.usfmreader.presentation.model.Chapter
import org.wa.usfmreader.presentation.model.Language
import org.wa.usfmreader.presentation.viewmodel.BookViewModel
import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import org.wa.usfmreader.presentation.viewmodel.LanguageViewModel
import tornadofx.*

class TopControls : View("Top") {

    val selectedLanguage = SimpleObjectProperty<Language>()
    val selectedBook = SimpleObjectProperty<Book>()
    val selectedChapter = SimpleObjectProperty<Chapter>()

    val languageViewModel = LanguageViewModel(selectedLanguage)
    val bookViewModel = BookViewModel(selectedBook)
    val chapterViewModel = ChapterViewModel(selectedChapter)

    lateinit var languagesCombobox: ComboBox<Language>
    lateinit var booksCombobox: ComboBox<Book>
    lateinit var chaptersCombobox: ComboBox<Chapter>

    val languages: ObservableList<Language> =
            mutableListOf<Language>().observable()

    init {
        languageViewModel.getLanguages()
                .observeOn(JavaFxScheduler.platform())
                .subscribe { it ->
                    val newLangs = it
                            .sortedBy { it.name }
                            .map {
                        Language(
                                slug = it.slug,
                                name = it.name,
                                direction = it.direction,
                                books = it.books
                                        .sortedBy { it.sort }
                                        .map {
                                    Book(
                                            slug = it.slug,
                                            name = it.name,
                                            sort = it.sort,
                                            usfmUrl = it.usfmUrl,
                                            chapters = it.chapters.map {
                                                Chapter(
                                                        number = it.number,
                                                        text = it.text
                                                )
                                            }.observable()
                                    )
                                }.observable()
                        )
                    }

                    languages.clear()
                    languages.addAll(newLangs)
                }
    }

    override val root = hbox {
        spacing = 20.0

        languagesCombobox = combobox(selectedLanguage, languages) {
            selectionModel.selectFirst()
            cellFormat {
                text = it.name
            }
            selectionModel.selectedItemProperty().onChange {
                booksCombobox.selectionModel.selectFirst()
                chaptersCombobox.selectionModel.selectFirst()
            }
        }
        booksCombobox = combobox(selectedBook, languageViewModel.books as List<Book>) {
            selectionModel.selectFirst()
            cellFormat {
                text = it.name
            }
            selectionModel.selectedItemProperty().onChange {
                chaptersCombobox.selectionModel.selectFirst()
            }
        }
        chaptersCombobox = combobox(selectedChapter, bookViewModel.chapters as List<Chapter>) {
            selectionModel.selectFirst()
            cellFormat {
                text = it.number.toString()
            }
        }
    }
}
