package org.wa.usfmreader.domain.entities

data class LanguageEntity(
        var slug: String,
        var name: String,
        var books: List<BookEntity>
)