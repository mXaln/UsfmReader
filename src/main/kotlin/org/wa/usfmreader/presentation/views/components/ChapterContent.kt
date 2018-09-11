package org.wa.usfmreader.presentation.views.components

import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.text.FontWeight
import org.wa.usfmreader.presentation.viewmodel.BookViewModel
import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import org.wa.usfmreader.presentation.viewmodel.MainViewModel
import tornadofx.*

class ChapterContent : View("Chapter") {
    val selectedChapter: ChapterViewModel by inject()
    val selectedBook: BookViewModel by inject()

    val viewModel: MainViewModel by inject()

    override val root = vbox {
        hbox {
            spacing = 2.0
            alignment = Pos.TOP_CENTER
            style {
                fontWeight = FontWeight.BOLD
                fontSize = 25.px
            }
            label(viewModel.bookNameProperty)
            label(viewModel.chapterNumberProperty) {
//                hiddenWhen {
//                    selectedBook.empty
//                }
            }
        }

        scrollpane {
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            padding = insets(10)

            stackpane {

                imageview("loader.gif") {
                    fitWidth = 40.0
                    fitHeight = 40.0
                    visibleWhen {
                        selectedChapter.text.isBlank()
                                .and(!selectedBook.empty)
                    }

                }
                text (selectedChapter.text) {
                    prefHeight = 600.0
                    wrappingWidth = 1000.0
                    style {
                        wrapText = true
                        fontSize = 20.px
                    }
                }
            }
        }
    }
}
