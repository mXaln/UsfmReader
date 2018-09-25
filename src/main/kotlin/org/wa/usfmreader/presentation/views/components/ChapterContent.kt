package org.wa.usfmreader.presentation.views.components

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.image.ImageView
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import tornadofx.*

class ChapterContent : View("Chapter") {
    var bookLabel: Label by singleAssign()
    var chapterLabel: Label by singleAssign()
    var imageLoader: ImageView by singleAssign()
    var chapterText: Text by singleAssign()

    override val root = vbox {
        hbox {
            spacing = 2.0
            alignment = Pos.TOP_CENTER
            style {
                fontWeight = FontWeight.BOLD
                fontSize = 25.px
            }
            bookLabel = label()
            chapterLabel = label()
        }

        scrollpane {
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            padding = insets(10)

            stackpane {

                alignment = Pos.TOP_CENTER

                imageLoader = imageview("loader.gif") {
                    fitWidth = 40.0
                    fitHeight = 40.0

                }
                chapterText = text {
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
