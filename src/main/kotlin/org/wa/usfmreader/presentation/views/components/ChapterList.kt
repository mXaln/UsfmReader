package org.wa.usfmreader.presentation.views.components

import javafx.scene.text.FontWeight
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.presentation.viewmodel.ChapterViewModel
import tornadofx.*

class ChapterList : Fragment() {
    private val viewModel: ChapterViewModel by inject()

    override val root = vbox {
        label("Select chapter:") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }
        viewModel.comboBox = combobox<ChapterData> {
            items = viewModel.chapters
            bind(viewModel.itemProperty)

            cellFormat {
                text = it.number.toString()
            }
            selectionModel.selectedItemProperty().onChange {
                viewModel.item = it
            }
            useMaxWidth = true

            disableWhen { viewModel.bookEmpty }
        }
    }
}