package org.wa.usfmreader.presentation.viewmodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.wa.usfmreader.data.entities.ChapterData
import tornadofx.*

class ChapterViewModel(chapterData: ChapterData? = null): ItemViewModel<ChapterData>(chapterData) {
    val number = bind(autocommit = true) {
        SimpleIntegerProperty(item?.number ?: 1)
    }
    val text = bind(autocommit = true) {
        SimpleStringProperty(item?.text ?: "")
    }

    val selectedBook: BookViewModel by inject()

    fun getNextChapter(): ChapterData {
        val nextChapter = selectedBook.item.chapters
                .singleOrNull { it.number == item.number + 1 }

        if (nextChapter != null) {
            return nextChapter
        }

        return item
    }

    fun getPreviousChapter(): ChapterData {
        val prevChapter = selectedBook.item.chapters
                .singleOrNull { it.number == item.number - 1 }

        if (prevChapter != null) {
            return prevChapter
        }

        return item
    }
}