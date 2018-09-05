package org.wa.usfmreader.presentation.viewmodel

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.presentation.model.Chapter
import tornadofx.*

class ChapterViewModel(property: ObjectProperty<Chapter>): ItemViewModel<Chapter>(itemProperty = property) {
    val number = bind(autocommit = true) {
        SimpleIntegerProperty(item?.number ?: 1)
    }
    val text = bind(autocommit = true) {
        SimpleStringProperty(item?.text ?: "")
    }
}