package org.wa.usfmreader.presentation.views

import javafx.scene.layout.HBox
import org.wa.usfmreader.presentation.views.components.BookList
import org.wa.usfmreader.presentation.views.components.ChapterList
import org.wa.usfmreader.presentation.views.components.LanguageList
import tornadofx.*

class TopControls : View("Top") {

    private val languageList = find(LanguageList::class)
    private val bookList = find(BookList::class)
    private val chapterList = find(ChapterList::class)

    override val root = HBox()

    init {
        root.apply {
            spacing = 20.0
        }

        root += languageList
        root += bookList
        root += chapterList
    }
}
