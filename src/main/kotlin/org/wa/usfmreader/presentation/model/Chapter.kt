package org.wa.usfmreader.presentation.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Chapter(number: Int, text: String) {
    val numberProperty = SimpleIntegerProperty(this, "number", number)
    var number: Int by numberProperty

    val textProperty = SimpleStringProperty(this, "text", text)
    var text: String by textProperty

    override fun toString(): String {
        return number.toString()
    }
}