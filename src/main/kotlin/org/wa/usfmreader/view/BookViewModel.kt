package org.wa.usfmreader.view

import javafx.beans.property.ObjectProperty
import org.wa.usfmreader.model.Book
import tornadofx.*

class BookViewModel(property: ObjectProperty<Book>): ItemViewModel<Book>(itemProperty = property) {
    val slug = bind(autocommit = true) {
        item?.slugProperty
    }
    val name = bind(autocommit = true) {
        item?.nameProperty
    }
    val usfmUrl = bind(autocommit = true) {
        item?.usfmUrlProperty
    }
    val chapters = bind(autocommit = true) {
        item?.chaptersProperty
    }
}