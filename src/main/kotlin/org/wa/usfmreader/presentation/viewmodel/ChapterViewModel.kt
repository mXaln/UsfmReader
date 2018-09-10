package org.wa.usfmreader.presentation.viewmodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import org.wa.usfmreader.data.entities.ChapterData
import tornadofx.*

class ChapterViewModel(chapterData: ChapterData? = null): ItemViewModel<ChapterData>(chapterData) {
    val number = bind(autocommit = true) {
        SimpleIntegerProperty(item?.number ?: 1)
    }
    val text = bind(autocommit = true) {
        SimpleStringProperty(item?.text ?: "")
    }

    val chapters: ObservableList<ChapterData> =
            mutableListOf<ChapterData>().observable()

    lateinit var comboBox: ComboBox<ChapterData>

    val bookViewModel: BookViewModel by inject()
    val bookEmpty = bookViewModel.empty

    fun getNextChapter(): ChapterData {
        val nextChapter = bookViewModel.item.chapters
                .singleOrNull { it.number == item.number + 1 }

        if (nextChapter != null) {
            return nextChapter
        }

        return item
    }

    fun getPreviousChapter(): ChapterData {
        val prevChapter = bookViewModel.item.chapters
                .singleOrNull { it.number == item.number - 1 }

        if (prevChapter != null) {
            return prevChapter
        }

        return item
    }

    fun selectFirst() {
        comboBox.selectionModel.selectFirst()
    }

    fun clear() {
        comboBox.items.clear()
    }
}