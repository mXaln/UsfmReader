package org.wa.usfmreader.domain.entities

data class BookEntity(
        var slug: String,
        var name: String,
        var usfmUrl: String,
        var chapters: List<ChapterEntity>

)