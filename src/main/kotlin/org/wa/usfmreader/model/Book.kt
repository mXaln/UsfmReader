package org.wa.usfmreader.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Book(slug: String, name: String) {
    private val slugProperty = SimpleStringProperty(this, "slug", slug)
    var slug: String by slugProperty

    private val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    override fun toString(): String {
        return name
    }
}