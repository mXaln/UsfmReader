package org.wa.usfmreader.data.entities

data class LanguageData(
        var slug: String,
        var name: String,
        var books: List<BookData>
)