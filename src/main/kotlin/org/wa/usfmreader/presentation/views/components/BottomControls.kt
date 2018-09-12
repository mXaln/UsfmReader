package org.wa.usfmreader.presentation.views.components

import javafx.beans.binding.Bindings
import org.wa.usfmreader.presentation.viewmodel.MainViewModel
import tornadofx.*

class BottomControls : View("Bottom") {
    private val viewModel: MainViewModel by inject()

    override val root = hbox {
        spacing = 20.0
        button("Previous") {
            disableWhen {
                Bindings.isNull(viewModel.chapterProperty)
            }
            action {
                viewModel.handlePreviousChapterClick()
            }
        }
        button("Next") {
            disableWhen {
                Bindings.isNull(viewModel.chapterProperty)
            }
            action {
                viewModel.handleNextChapterClick()
            }
        }
    }
}
