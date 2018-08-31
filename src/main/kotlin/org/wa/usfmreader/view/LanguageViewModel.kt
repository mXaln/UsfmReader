package org.wa.usfmreader.view

import javafx.beans.property.ObjectProperty
import javafx.beans.property.Property
import org.wa.usfmreader.model.Language
import tornadofx.*

class LanguageViewModel(property: ObjectProperty<Language>): ItemViewModel<Language>(itemProperty = property) {
    val slug = bind(autocommit = true) {
        item?.slugProperty
    }
    val name = bind(autocommit = true) {
        item?.nameProperty
    }
    val books = bind(autocommit = true) {
        item?.booksProperty
    }
}