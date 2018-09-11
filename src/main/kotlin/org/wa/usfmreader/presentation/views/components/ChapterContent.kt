package org.wa.usfmreader.presentation.views.components

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.text.FontWeight
import org.wa.usfmreader.presentation.viewmodel.MainViewModel
import tornadofx.*

class ChapterContent : View("Chapter") {
    private val viewModel: MainViewModel by inject()
    private val topControls: TopControls by inject()

    override val root = vbox {
        hbox {
            spacing = 2.0
            alignment = Pos.TOP_CENTER
            style {
                fontWeight = FontWeight.BOLD
                fontSize = 25.px
            }
            label(viewModel.bookProperty.select { SimpleStringProperty(it.name) })
            label(viewModel.chapterProperty.select {
                SimpleStringProperty(it.number.toString())
            }) {
                hiddenWhen {
                    topControls.bookCombobox.selectionModel.selectedItemProperty().isNull
                }
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
                        topControls.chapterCombobox.selectionModel.selectedItemProperty().isNull
                                .and(topControls.bookCombobox.selectionModel.selectedItemProperty().isNotNull)
                    }

                }
                text (viewModel.chapterProperty.select {
                    SimpleStringProperty(it.text)
                }) {
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
