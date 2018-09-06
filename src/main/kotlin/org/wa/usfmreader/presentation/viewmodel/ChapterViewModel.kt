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
}