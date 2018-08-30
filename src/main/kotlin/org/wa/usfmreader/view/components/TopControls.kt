package org.wa.usfmreader.view.components

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import org.wa.usfmreader.model.Book
import tornadofx.*

class TopControls : View("Top") {
    val selectedBook = SimpleObjectProperty<Book>()
    val selectedChapter = SimpleIntegerProperty()

    override val root = hbox {
        spacing = 20.0

        combobox(selectedBook, listOf(
                Book("gen", "Genesis"),
                Book("exo", "Exodus"),
                Book("lev", "Leviticus")).observable()) {
            selectionModel.selectFirst()
        }
        combobox(selectedChapter, listOf(1, 2, 3, 4, 5, 6, 7).observable()) {
            selectionModel.selectFirst()
        }
    }
}
