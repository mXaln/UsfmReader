package org.wa.usfmreader.presentation.components

import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import tornadofx.*

class ChapterContent : View("Chapter") {
    val chapterViewModel: ChapterViewModel by inject()

    override val root = vbox {

        scrollpane {
            text("Chapter") {

            }
        }
    }
}
