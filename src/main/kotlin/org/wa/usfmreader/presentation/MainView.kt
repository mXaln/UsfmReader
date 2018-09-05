package org.wa.usfmreader.presentation

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import org.wa.usfmreader.presentation.components.BottomControls
import org.wa.usfmreader.presentation.components.ChapterContent
import org.wa.usfmreader.presentation.components.TopControls
import tornadofx.*

class MainView : View("Hello TornadoFX") {
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
            alignment = Pos.BASELINE_CENTER
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