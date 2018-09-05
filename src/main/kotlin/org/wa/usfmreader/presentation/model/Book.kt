package org.wa.usfmreader.presentation.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import tornadofx.*

class Book(slug: String, name: String, sort: Int, usfmUrl: String, chapters: ObservableList<Chapter>) {
    val slugProperty = SimpleStringProperty(this, "slug", slug)
    var slug: String by slugProperty

    val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    val sortProperty = SimpleIntegerProperty(this, "sort", sort)
    var sort: Int by sortProperty

    val usfmUrlProperty = SimpleStringProperty(this, "usfmUrl", usfmUrl)
    var usfmUrl: String by usfmUrlProperty

    val chaptersProperty = SimpleListProperty<Chapter>(this, "chapters", chapters)
    var chapters: ObservableList<Chapter> by chaptersProperty

    override fun toString(): String {
        return name
    }
}