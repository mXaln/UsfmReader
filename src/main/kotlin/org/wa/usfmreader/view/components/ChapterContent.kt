package org.wa.usfmreader.view.components

import javafx.beans.property.SimpleObjectProperty
import org.wa.usfmreader.model.Chapter
import org.wa.usfmreader.view.ChapterViewModel
import tornadofx.*

class ChapterContent : View("Chapter") {
    val selectedChapter = SimpleObjectProperty<Chapter>()
    val chapterItemViewModel = ChapterViewModel(selectedChapter)

    override val root = vbox {

        scrollpane {
            text(chapterItemViewModel.text) {

            }
        }
    }
}
