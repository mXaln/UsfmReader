package org.wa.usfmreader.presentation.model

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import tornadofx.*

class Language(slug: String, name: String, direction: String, books: ObservableList<Book>) {
    val slugProperty = SimpleStringProperty(this, "slug", slug)
    var slug: String by slugProperty

    val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    val directionProperty = SimpleStringProperty(this, "direction", direction)
    var direction: String by directionProperty

    val booksProperty = SimpleListProperty<Book>(this, "books", books)
    var books: ObservableList<Book> by booksProperty

    override fun toString(): String {
        return name
    }
}