package org.wa.usfmreader.presentation.components

import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import javafx.scene.text.FontWeight
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.viewmodel.BookViewModel
import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import org.wa.usfmreader.presentation.viewmodel.LanguageViewModel
import tornadofx.*

class TopControls : View("Top") {

    val selectedLanguage: LanguageViewModel by inject()
    val selectedBook: BookViewModel by inject()
    val selectedChapter: ChapterViewModel by inject()

    lateinit var languagesCombobox: ComboBox<LanguageData>
    lateinit var booksCombobox: ComboBox<BookData>
    lateinit var chaptersCombobox: ComboBox<ChapterData>

    val languages: ObservableList<LanguageData> =
            mutableListOf<LanguageData>().observable()

    init {
        selectedLanguage.getLanguages()
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.computation())
                .subscribe { it ->
                    val newLangs = it
                            .sortedBy { it.name }

                    languages.clear()
                    languages.addAll(newLangs)
                }

    }

    override val root = hbox {
        spacing = 20.0

        vbox {
            label("Select language:") {
                style {
                    fontWeight = FontWeight.BOLD
                }
            }
            languagesCombobox = combobox {
                items = languages
                bind(selectedLanguage.itemProperty)

                cellFormat {
                    text = it.name
                }
                selectionModel.selectedItemProperty().onChange {
                    booksCombobox.items = it?.books?.observable()
                }
                useMaxWidth = true
            }
        }

        vbox {
            label("Select book:") {
                style {
                    fontWeight = FontWeight.BOLD
                }
            }
            booksCombobox = combobox {
                items = selectedLanguage.books.value
                bind(selectedBook.itemProperty)

                cellFormat {
                    text = it.name
                }
                selectionModel.selectedItemProperty().onChange {
                    if (it != null) {
                        selectedChapter.text.value = ""
                        selectedBook.getBook(it)
                                .observeOn(JavaFxScheduler.platform())
                                .subscribeOn(Schedulers.computation())
                                .subscribe {
                                    selectedBook.item = it
                                    chaptersCombobox.items = it?.chapters?.observable()
                                    chaptersCombobox.selectionModel.selectFirst()
                                }
                    }
                    else {
                        chaptersCombobox.items = listOf<ChapterData>().observable()
                    }
                }
                useMaxWidth = true
                disableWhen { selectedLanguage.empty }
            }
        }

        vbox {
            label("Select chapter:") {
                style {
                    fontWeight = FontWeight.BOLD
                }
            }
            chaptersCombobox = combobox {
                items = selectedBook.chapters.value
                bind(selectedChapter.itemProperty)

                cellFormat {
                    text = it.number.toString()
                }
                selectionModel.selectedItemProperty().onChange {
                    selectedChapter.item = it
                }
                useMaxWidth = true

                disableWhen { selectedBook.empty }
            }
        }
    }
}
