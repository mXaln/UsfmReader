package org.wa.usfmreader.presentation.views.components

import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.FontWeight
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData
import tornadofx.*

class TopControls : View("Top") {
    var imageLoader: ImageView by singleAssign()
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

                imageLoader = imageview("loader.gif") {
                    fitWidth = 25.0
                    fitHeight = 25.0
                }

                vbox {
                    label("Select language:") {
                        style {
                            fontWeight = FontWeight.BOLD
                        }
                    }

                    languageCombobox = combobox {
                        cellFormat {
                            text = it.name
                        }

                        useMaxWidth = true
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
                    cellFormat {
                        text = it.name
                    }

                    useMaxWidth = true
                }
            }

            vbox {
                label("Select chapter:") {
                    style {
                        fontWeight = FontWeight.BOLD
                    }
                }
                chapterCombobox = combobox {
                    cellFormat {
                        text = it.number.toString()
                    }

                    useMaxWidth = true
                }
            }
        }
    }
}
