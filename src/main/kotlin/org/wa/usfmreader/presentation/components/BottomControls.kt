package org.wa.usfmreader.presentation.components

import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import tornadofx.*

class BottomControls : View("Bottom") {
    val selectedChapter: ChapterViewModel by inject()

    override val root = hbox {
        spacing = 20.0
        button("Previous") {
            disableWhen {
                selectedChapter.empty
            }
            action {
                selectedChapter.item = selectedChapter.getPreviousChapter()
            }
        }
        button("Next") {
            disableWhen { selectedChapter.empty }
            action {
                selectedChapter.item = selectedChapter.getNextChapter()
            }
        }
    }
}
