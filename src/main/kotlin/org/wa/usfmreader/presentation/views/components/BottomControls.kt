package org.wa.usfmreader.presentation.views.components

import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import tornadofx.*

class BottomControls : View("Bottom") {
    private val chapterViewModel: ChapterViewModel by inject()

    override val root = hbox {
        spacing = 20.0
        button("Previous") {
            disableWhen {
                chapterViewModel.empty
            }
            action {
                chapterViewModel.item = chapterViewModel.getPreviousChapter()
            }
        }
        button("Next") {
            disableWhen { chapterViewModel.empty }
            action {
                chapterViewModel.item = chapterViewModel.getNextChapter()
            }
        }
    }
}
