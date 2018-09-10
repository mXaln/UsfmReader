package org.wa.usfmreader.presentation.views.components

import javafx.scene.text.FontWeight
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.presentation.viewmodel.LanguageViewModel
import tornadofx.*

class LanguageList : Fragment() {
    private val viewModel: LanguageViewModel by inject()

    override val root = vbox {
        label("Select language:") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        combobox<LanguageData> {
            items = viewModel.languages
            bind(viewModel.itemProperty)

            cellFormat {
                text = it.name
            }

            selectionModel.selectedItemProperty().onChange {
                viewModel.onLanguageSelected(it)
            }
            useMaxWidth = true
        }
    }
}