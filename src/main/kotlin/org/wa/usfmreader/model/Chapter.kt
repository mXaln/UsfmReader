package org.wa.usfmreader.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
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