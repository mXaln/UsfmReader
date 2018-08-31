package org.wa.usfmreader.view

import javafx.beans.property.ObjectProperty
import org.wa.usfmreader.model.Book
import org.wa.usfmreader.model.Chapter
import tornadofx.*

class ChapterViewModel(property: ObjectProperty<Chapter>): ItemViewModel<Chapter>(itemProperty = property) {
    val number = bind(autocommit = true) {
        item?.numberProperty
    }
    val text = bind(autocommit = true) {
        item?.textProperty
    }
}