package org.wa.usfmreader.model

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class Language(slug: String, name: String) {
    private val slugProperty = SimpleStringProperty(this, "slug", slug)
    var slug: String by slugProperty

    private val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    private val booksProperty = SimpleListProperty<Book>(FXCollections.observableArrayList())
    var books: ObservableList<Book> by booksProperty

    override fun toString(): String {
        return name
    }
}