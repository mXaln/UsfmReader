package org.wa.usfmreader.data.entities

data class BookData(
        var slug: String,
        var name: String,
        var usfmUrl: String,
        var chapters: List<ChapterData>
)