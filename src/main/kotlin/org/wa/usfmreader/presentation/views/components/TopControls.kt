package org.wa.usfmreader.presentation.views.components

import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.layout.HBox
import javafx.scene.text.FontWeight
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.viewmodel.MainViewModel
import org.wa.usfmreader.presentation.views.MainView
import tornadofx.*

class TopControls : View("Top") {

    val viewModel: MainViewModel by inject()

    var languageCombobox: ComboBox<LanguageData> by singleAssign()
    var bookCombobox: ComboBox<BookData> by singleAssign()
    var chapterCombobox: ComboBox<ChapterData> by singleAssign()

    override val root = HBox()

    init {

        root.apply {
            spacing = 20.0

            hbox {

                spacing = 10.0
                alignment = Pos.BOTTOM_CENTER

                imageview("loader.gif") {
                    fitWidth = 25.0
                    fitHeight = 25.0
                    visibleWhen {
                        Bindings.isEmpty(viewModel.languages)
                    }

                }

                vbox {
                    label("Select language:") {
                        style {
                            fontWeight = FontWeight.BOLD
                        }
                    }

                    languageCombobox = combobox {
                        items = viewModel.languages

                        cellFormat {
                            text = it.name
                        }

                        useMaxWidth = true
                        disableWhen {
                            Bindings.isEmpty(viewModel.languages)
                        }
                    }
                }
            }

            vbox {
                label("Select book:") {
                    style {
                        fontWeight = FontWeight.BOLD
                    }
                }

                bookCombobox = combobox {
                    items = viewModel.books

                    cellFormat {
                        text = it.name
                    }

                    useMaxWidth = true
                    disableWhen {
                        Bindings.isNull(viewModel.languageProperty)
                    }
                }
            }

            vbox {
                label("Select chapter:") {
                    style {
                        fontWeight = FontWeight.BOLD
                    }
                }
                chapterCombobox = combobox {
                    items = viewModel.chapters

                    cellFormat {
                        text = it.number.toString()
                    }

                    useMaxWidth = true

                    disableWhen {
                        Bindings.isEmpty(viewModel.chapters)
                    }
                }
            }
        }
    }
}
