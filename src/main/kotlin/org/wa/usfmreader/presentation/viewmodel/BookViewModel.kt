package org.wa.usfmreader.presentation.viewmodel

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.presentation.model.Book
import org.wa.usfmreader.presentation.model.Chapter
import tornadofx.*

class BookViewModel(property: ObjectProperty<Book>): ItemViewModel<Book>(itemProperty = property) {
    val slug = bind(autocommit = true) {
        SimpleStringProperty(item?.slug ?: "")
    }
    val name = bind(autocommit = true) {
        SimpleStringProperty(item?.name ?: "")
    }
    val usfmUrl = bind(autocommit = true) {
        SimpleStringProperty(item?.usfmUrl ?: "")
    }
    val chapters = bind(autocommit = true) {
        SimpleListProperty<Chapter>(FXCollections.observableArrayList(item?.chapters ?: listOf()))
    }
}