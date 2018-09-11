package org.wa.usfmreader.presentation.views

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import org.wa.usfmreader.presentation.views.components.BottomControls
import org.wa.usfmreader.presentation.views.components.ChapterContent
import org.wa.usfmreader.presentation.views.components.TopControls
import tornadofx.*

class MainView : View("Bible Reader") {
    override val root = BorderPane()

    private val topControls: TopControls by inject()
    private val bottomControls: BottomControls by inject()
    private val chapterContent: ChapterContent by inject()

    init {
        root.apply {
            setMinSize(1024.0, 768.0)
        }

        root.top = topControls.root.apply {
            alignment = Pos.CENTER
            borderpaneConstraints {
                margin = Insets(20.0, 0.0, 20.0, 0.0)
            }
        }

        root.center = chapterContent.root.apply {
            alignment = Pos.TOP_CENTER
            borderpaneConstraints {
                margin = Insets(0.0, 20.0, 0.0, 20.0)
            }
        }

        root.bottom = bottomControls.root.apply {
            alignment = Pos.CENTER
            borderpaneConstraints {
                margin = Insets(20.0, 0.0, 20.0, 0.0)
            }
        }
    }
}