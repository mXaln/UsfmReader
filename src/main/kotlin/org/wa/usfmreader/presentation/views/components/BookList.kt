package org.wa.usfmreader.presentation.views.components

import javafx.scene.text.FontWeight
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.presentation.viewmodel.BookViewModel
import tornadofx.*

class BookList : Fragment() {
    private val viewModel: BookViewModel by inject()

    override val root = vbox {
        label("Select book:") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        combobox<BookData> {
            items = viewModel.books
            bind(viewModel.itemProperty)

            cellFormat {
                text = it.name
            }
            selectionModel.selectedItemProperty().onChange {
                viewModel.onBookSelected(it)
            }
            useMaxWidth = true
            disableWhen { viewModel.languageEmpty }
        }
    }
}