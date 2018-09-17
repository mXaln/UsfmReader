package org.wa.usfmreader.presentation.views.components

import javafx.scene.control.Button
import tornadofx.*

class BottomControls : View("Bottom") {
    var prevButton: Button by singleAssign()
    var nextButton: Button by singleAssign()

    override val root = hbox {
        spacing = 20.0
        prevButton = button("Previous")
        nextButton = button("Next")
    }
}
