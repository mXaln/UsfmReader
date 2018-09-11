package org.wa.usfmreader.presentation.views.components

import org.wa.usfmreader.presentation.viewmodel.MainViewModel
import tornadofx.*

class BottomControls : View("Bottom") {
    private val viewModel: MainViewModel by inject()
    private val topControls: TopControls by inject()

    override val root = hbox {
        spacing = 20.0
        button("Previous") {
            disableWhen {
                topControls.chapterCombobox.selectionModel.selectedItemProperty().isNull
            }
            action {
                topControls.chapterCombobox.selectionModel.selectPrevious()
            }
        }
        button("Next") {
            disableWhen {
                topControls.chapterCombobox.selectionModel.selectedItemProperty().isNull
            }
            action {
                topControls.chapterCombobox.selectionModel.selectNext()
            }
        }
    }
}
